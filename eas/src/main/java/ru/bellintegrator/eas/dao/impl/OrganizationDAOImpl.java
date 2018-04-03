package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.exception.MyException;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Organization> all() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        List<Organization> organizations = query.getResultList();
        if (organizations.isEmpty()) {
            return new ArrayList<>();
        }
        return organizations;
    }

    @Override
    public List<Organization> loadOrganization(String name, int inn, boolean isActive) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);

        criteriaQuery.where(cb.like(organizationRoot.get("name"), cb.parameter(String.class, "name1")),
                cb.like(organizationRoot.get("inn"), cb.parameter(String.class, "inn1")),
                cb.equal(organizationRoot.<Boolean>get("isActive"), "isActive1"));
        criteriaQuery.select(organizationRoot);

        TypedQuery<Organization> tq = em.createQuery(criteriaQuery);
        tq.setParameter("name1", "%" + name + "%");
        tq.setParameter("inn1", "%" + inn + "%");
        tq.setParameter("isActive1", isActive);

        List<Organization> organizationsResult = tq.getResultList();

        for (Organization organization : organizationsResult) {
            System.out.println(organization);
        }
        // переделать
        return organizationsResult;
    }

    @Override
    public Organization loadById(Long id) throws MyException {
        Organization organization = em.find(Organization.class, id);
        if (!organization.isActive()) {
            throw new MyException("Organization is not activated");
        }
        return organization;
    }

    @Override
    public boolean update(Long id, Organization organization) {
        Organization organizationResult = em.find(Organization.class, id);
        organizationResult.setId(organization.getId());
        organizationResult.setName(organization.getName());
        organizationResult.setFullName(organization.getFullName());
        organizationResult.setInn(organization.getInn());
        organizationResult.setKpp(organization.getKpp());
        organizationResult.setAddress(organization.getAddress());
        organizationResult.setPhone(organization.getPhone());
        organizationResult.setActive(organization.isActive());
        em.merge(organizationResult);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Organization organization = em.find(Organization.class, id);
        if (organization != null) {
            em.remove(organization);
        }
        return true;
    }

    @Override
    public boolean save(Organization organization) {
        if (organization.getId() == null) {
            em.persist(organization);
        } else {
            update(organization.getId(), organization);
        }
        return true;
    }

    @Override
    public boolean register(String login, String password, String name) throws MyException, NoSuchAlgorithmException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        List<Organization> organizations = query.getResultList();

        for (Organization org : organizations) {
            if (org.getName().equals(name)) {
                throw new MyException("This organization is already registered");
            }
        }

        Organization organization = new Organization();
        organization.setName(name);
        organization.setFullName("");
        organization.setLogin(login);
        organization.setPassword(getHashSHA2forPassword(password));
        organization.setInn(0);
        organization.setKpp(0);
        organization.setAddress("");
        organization.setPhone(0);
        organization.setActive(false);
        organization.setHashActive(getHashForActive());
        organization.setOffices(null);

        em.persist(organization);
        return true;
    }

    @Override
    public boolean login(String login, String password) throws NoSuchAlgorithmException {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> register = criteria.from(Organization.class);
        criteria.where(builder.equal(register.get("login"), login),
                builder.equal(register.get("password"), getHashSHA2forPassword(password)));
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult() != null;
    }

    @Override
    public boolean activation(String hashCode) throws MyException {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);
        criteria.where(builder.equal(organizationRoot.get("hashActive"), hashCode));
        TypedQuery<Organization> query = em.createQuery(criteria);
        Organization reg = query.getSingleResult();

        if (reg.isActive()) {
            throw new MyException("Organization is already activated");
        }

        if (reg.getHashActive().equals(hashCode)) {
            reg.setActive(true);
        }
        return true;
    }

    private String getHashSHA2forPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] digest = sha.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    private String getHashForActive() throws NoSuchAlgorithmException {
        String letters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randString = new StringBuilder();
        int count = (int) (Math.random() * 30);
        for (int i = 0; i < count; i++)
            randString.append(letters.charAt((int) (Math.random() * letters.length())));
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] digest = sha.digest(randString.toString().getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}

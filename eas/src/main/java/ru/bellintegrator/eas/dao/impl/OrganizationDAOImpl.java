package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public List<Organization> all() throws MyException {
        String sqlQuery = "SELECT o FROM Organization o";
        TypedQuery<Organization> query =
                em.createQuery(sqlQuery, Organization.class);
        List<Organization> organizations = query.getResultList();
        if (organizations.isEmpty()) {
            throw new MyException("List of organizations is empty");
        }
        return organizations;
    }

    @Transactional
    @Override
    public List<Organization> loadOrganization(String name, int inn, boolean isActive) throws MyException {
        if (name == null || inn < 0) {
            StringBuilder sb = new StringBuilder("Invalid name or inn: ").
                    append("name = ").append(name).
                    append("inn = ").append(inn);
            throw new MyException(sb.toString());
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = cb.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);

        criteriaQuery.where(cb.like(organizationRoot.get("name"), cb.parameter(String.class, "name1")),
                cb.like(organizationRoot.get("inn"), cb.parameter(String.class, "inn1")),//запрос падает, не верный тип
                cb.equal(organizationRoot.<Boolean>get("isActive"), "isActive1"));

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

    @Transactional
    @Override
    public Organization loadById(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        Organization organization = em.find(Organization.class, id);
        if (!organization.isActive()) {
            throw new MyException("Organization is not activated");
        }
        return organization;
    }

    @Transactional
    @Override
    public boolean update(Long id, Organization organization) throws MyException {
        if (id == null || id <= 0L || organization == null) {
            StringBuilder sb = new StringBuilder("Invalid id or organization: ").
                    append("id = ").append(id).
                    append(", organization = ").append(organization);
            throw new MyException(sb.toString());
        }
        organization.setId(id);
        em.merge(organization);
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }

        Organization organization = em.find(Organization.class, id);
        if (organization != null) {
            em.remove(organization);
        }
        return true;

    }

    @Transactional
    @Override
    public boolean save(Organization organization) throws MyException {
        if (organization == null) {
            StringBuilder sb = new StringBuilder("Invalid organization : ").
                    append("organization = ").append(organization);
            throw new MyException(sb.toString());
        }
        if (organization.getId() == null) {
            em.persist(organization);
        } else {
            update(organization.getId(), organization);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean register(String login, String password, String name) throws MyException, NoSuchAlgorithmException {
        if (login.isEmpty() || password.isEmpty() || name.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid login, password or name : ").
                    append("login = ").append(login).
                    append(", password = ").append(getHashSHA2forPassword(password)).
                    append(", name = ").append(name);
            throw new MyException(sb.toString());
        }
        TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
        List<Organization> list = query.getResultList();
        for (Organization org : list) {
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

    @Transactional
    @Override
    public boolean login(String login, String password) throws MyException, NoSuchAlgorithmException {
        if (login.isEmpty() || password.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid login or password : ").
                    append("login = ").append(login).
                    append(", password = ").append(getHashSHA2forPassword(password));
            throw new MyException(sb.toString());
        }
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = builder.createQuery(Organization.class);
        Root<Organization> register = criteria.from(Organization.class);
        criteria.where(builder.equal(register.get("login"), login),
                builder.equal(register.get("password"), getHashSHA2forPassword(password)));
        TypedQuery<Organization> query = em.createQuery(criteria);
        return query.getSingleResult() != null;
    }

    @Transactional
    @Override
    public boolean activation(String hashCode) throws MyException {
        if (hashCode.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid hashCode : ").
                    append("hashCode = ").append(hashCode);
            throw new MyException(sb.toString());
        }
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

    private String getHashSHA2forPassword(String password) throws MyException, NoSuchAlgorithmException {
        if (password.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid password : ").
                    append("password = ").append(password);
            throw new MyException(sb.toString());
        }
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

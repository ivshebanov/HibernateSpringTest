package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
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
        try {
            String sqlQuery = "SELECT o FROM Organization o";
            TypedQuery<Organization> query =
                    em.createQuery(sqlQuery, Organization.class);
            List<Organization> organizations = query.getResultList();
            if (organizations.isEmpty()) {
                return null;
            }
            return organizations;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Organization load(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        try {
            Organization organization = em.find(Organization.class, id);
            if (organization.isActive()) {
                return organization;
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return null;
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
        try {
            organization.setId(id);
            em.merge(organization);
            return true;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        try {
            Organization organization = em.find(Organization.class, id);
            if (organization != null) {
                em.remove(organization);
            }
            return true;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean save(Organization organization) throws MyException {
        if (organization == null) {
            StringBuilder sb = new StringBuilder("Invalid organization : ").
                    append("organization = ").append(organization);
            throw new MyException(sb.toString());
        }
        try {
            if (organization.getId() == 0) {
                em.persist(organization);
            } else {
                update(organization.getId(), organization);
            }
            return true;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

    }

    @Transactional
    @Override
    public boolean register(String login, String password, String name) throws MyException {
        if (login.isEmpty() || password.isEmpty() || name.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid login, password or name : ").
                    append("login = ").append(login).
                    append(", password = ").append(password).
                    append(", name = ").append(name);
            throw new MyException(sb.toString());
        }
//        try {
//            TypedQuery<Register> query = em.createQuery("SELECT r FROM Register r", Register.class);
//            List<Register> list = query.getResultList();
//            for (Register us : list) {
//                if (us.getName().equals(name)) {
//                    return false;
//                }
//            }
//            Register register = new Register();
//            register.setName(name);
//            register.setLogin(login);
//            register.setPassword(getHashSHA2forPassword(password));
//            register.setHashActive(getHashForActive());
//
//            Organization organization = new Organization();
//            organization.setName(name);
//            organization.setActive(false);
//            organization.setAddress("");
//            organization.setFullName("");
//            organization.setInn(0);
//            organization.setKpp(0);
//            organization.setPhone(0);
//            organization.setOffices(null);
//
//            em.persist(register);
//            em.persist(organization);
//            return true;
//        } catch (Exception e) {
//            throw new MyException(e.getMessage());
//        }
        return false;
    }

    @Transactional
    @Override
    public boolean login(String login, String password) throws MyException {
        if (login.isEmpty() || password.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid login or password : ").
                    append("login = ").append(login).
                    append(", password = ").append(password);
            throw new MyException(sb.toString());
        }
//        System.out.println(password);
//        System.out.println(getHashSHA2forPassword(password));
//        try {
//            CriteriaBuilder builder = em.getCriteriaBuilder();
//            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
//            Root<Register> register = criteria.from(Register.class);
//            criteria.where(builder.equal(register.get("login"), login),
//                    builder.equal(register.get("password"), getHashSHA2forPassword(password)));
//            TypedQuery<Register> query = em.createQuery(criteria);
//            return query.getSingleResult() != null;
//        } catch (Exception e) {
//            throw new MyException(e.getMessage());
//        }
        return false;
    }

    @Transactional
    @Override
    public boolean activation(String hashCode) throws MyException {
        if (hashCode.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid hashCode : ").
                    append("hashCode = ").append(hashCode);
            throw new MyException(sb.toString());
        }
//        try {
//            CriteriaBuilder builder = em.getCriteriaBuilder();
//            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
//            Root<Register> register = criteria.from(Register.class);
//            criteria.where(builder.equal(register.get("hashActive"), hashCode));
//            TypedQuery<Register> query = em.createQuery(criteria);
//            Register reg = query.getSingleResult();
//            System.out.println(reg.getName());
//
//            if (reg.getHashActive().equals(hashCode)) {
//                CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//                CriteriaQuery<Register> criteriaQuery = criteriaBuilder.createQuery(Register.class);
//                Root<Register> registerRoot = criteriaQuery.from(Register.class);
//                System.out.println("1");
//                Join<Register, Organization> company = registerRoot.join("name");
//                System.out.println("2");
//                criteriaQuery.select(registerRoot);
//                criteriaQuery.where(criteriaBuilder.equal(company.get("name"), reg.getName()));
//                em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
////
////                Join<Register, Organization> join = registerRoot.join("name", JoinType.LEFT);
////
////                criteriaQuery.multiselect(registerRoot, join.get("name"));
//
//
////                List<Organization> org = queryOrg.getResultList();
////                for (Organization o : org){
////                    System.out.println(o.getName());
////                }
////                org.setActive(true);
//            }
//            return true;
//        } catch (Exception e) {
//            throw new MyException(e.getMessage());
//        }
        return false;
    }

    private String getHashSHA2forPassword(String password) throws MyException {
        if (password.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid password : ").
                    append("password = ").append(password);
            throw new MyException(sb.toString());
        }
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha.digest(password.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new MyException(e.getMessage());
        }
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

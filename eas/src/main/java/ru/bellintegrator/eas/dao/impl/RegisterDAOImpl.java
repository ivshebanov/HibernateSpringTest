package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.RegisterDAO;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.model.Register;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Repository
public class RegisterDAOImpl implements RegisterDAO {

    private final EntityManager em;

    @Autowired
    public RegisterDAOImpl(EntityManager em) {
        this.em = em;
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
        try {
            TypedQuery<Register> query = em.createQuery("SELECT r FROM Register r", Register.class);
            List<Register> list = query.getResultList();
            for (Register us : list) {
                if (us.getName().equals(name)) {
                    return false;
                }
            }
            Register register = new Register();
            register.setName(name);
            register.setLogin(login);
            register.setPassword(getHashSHA2forPassword(password));
            register.setHashActive(getHashForActive());

            Organization organization = new Organization();
            organization.setName(name);
            organization.setActive(false);
            organization.setAddress("");
            organization.setFullName("");
            organization.setInn(0);
            organization.setKpp(0);
            organization.setPhone(0);
            organization.setOffices(null);

            em.persist(register);
            em.persist(organization);
            return true;
        } catch (Exception e) {
            return false;
        }
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
        System.out.println(password);
        System.out.println(getHashSHA2forPassword(password));
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
            Root<Register> register = criteria.from(Register.class);
            criteria.where(builder.equal(register.get("login"), login),
                    builder.equal(register.get("password"), getHashSHA2forPassword(password)));
            TypedQuery<Register> query = em.createQuery(criteria);
            return query.getSingleResult() != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean activation(String hashCode) throws MyException {
        if (hashCode.isEmpty()) {
            StringBuilder sb = new StringBuilder("Invalid hashCode : ").
                    append("hashCode = ").append(hashCode);
            throw new MyException(sb.toString());
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
            Root<Register> register = criteria.from(Register.class);
            criteria.where(builder.equal(register.get("hashActive"), hashCode));
            TypedQuery<Register> query = em.createQuery(criteria);
            Register reg = query.getSingleResult();
            System.out.println(reg.getName());

            if (reg.getHashActive().equals(hashCode)) {
                CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
                CriteriaQuery<Register> criteriaQuery = criteriaBuilder.createQuery(Register.class);
                Root<Register> registerRoot = criteriaQuery.from(Register.class);
                System.out.println("1");
                Join<Register, Organization> company = registerRoot.join("name");
                System.out.println("2");
                criteriaQuery.select(registerRoot);
                criteriaQuery.where(criteriaBuilder.equal(company.get("name"), reg.getName()));
                em.createQuery(criteriaQuery).getResultList().forEach(System.out::println);
//
//                Join<Register, Organization> join = registerRoot.join("name", JoinType.LEFT);
//
//                criteriaQuery.multiselect(registerRoot, join.get("name"));


//                List<Organization> org = queryOrg.getResultList();
//                for (Organization o : org){
//                    System.out.println(o.getName());
//                }
//                org.setActive(true);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
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
            e.printStackTrace();
            return null;
        }
    }

    private String getHashForActive() {
        try {
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

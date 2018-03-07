package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.dao.RegisterDAO;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.model.Register;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public boolean register(String login, String password, String name) {
        if (login.isEmpty() || password.isEmpty() || name.isEmpty()) {
            return false;
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
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean login(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            return false;
        }
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
    public boolean activation(String hashCode) {
        if (hashCode.isEmpty()) {
            return false;
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
            Root<Register> register = criteria.from(Register.class);
            criteria.where(builder.equal(register.get("hashActive"), hashCode));
            TypedQuery<Register> query = em.createQuery(criteria);
            Register reg = query.getSingleResult();
            if (reg.getHashActive().equals(hashCode)) {
                TypedQuery<Organization> queryOrg =
                        em.createQuery("SELECT o.name, o.isActive FROM Register r LEFT JOIN Organization o ON r.name=o.name " +
                                "WHERE o.name = :name", Organization.class);//не работает запрос
                query.setParameter("name", reg.getName());

                Organization organization = queryOrg.getSingleResult();
                organization.setActive(true);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private String getHashSHA2forPassword(String password) {
        if (password.isEmpty()) {
            return null;
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

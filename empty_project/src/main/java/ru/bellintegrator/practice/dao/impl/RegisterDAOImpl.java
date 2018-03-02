package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.RegisterDAO;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.Register;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        System.out.println("1 - register");
        try {
            Register register = new Register();
            Organization organization = new Organization();
//            em.persist(organization);
            organization.setName(register);
            register.setName(new Organization());
            register.setLogin(login);
            register.setPassword(password);
            em.persist(register);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean login(String login, String password) {
        System.out.println("2 - login");
        Register result;
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Register> criteria = builder.createQuery(Register.class);
            Root<Register> register = criteria.from(Register.class);
            criteria.where(builder.equal(register.get("login"), login),
                    builder.equal(register.get("password"), password));
            TypedQuery<Register> query = em.createQuery(criteria);
            result = query.getSingleResult();
        } catch (Exception e) {
            return false;
        }
        return result != null;
//        String queryString = "SELECT r FROM Register r WHERE r.login = :login AND r.password = :password";
//        TypedQuery<Register> query = em.createQuery(queryString, Register.class);
//        query.setParameter("login", login);
//        query.setParameter("password", password);
//         query.getSingleResult();
    }

    @Override
    public boolean activation(byte code) {

        return false;
    }
}

package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.RegisterDAO;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.Register;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RegisterDAOImpl implements RegisterDAO {

    private final EntityManager em;

    @Autowired
    public RegisterDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public boolean register(String login, String password, String name) {
        try {
            Register register = new Register();
            Organization organization = new Organization();
            organization.setName(register);
            register.setName(new Organization());
            register.setLogin(login);
            register.setPassword(password);
            em.getTransaction().begin();
            em.persist(register);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean login(String login, String password) {
        String queryString = "SELECT r FROM Register r WHERE r.login = :login AND r.password = :password";
        TypedQuery<Register> query = em.createQuery(queryString, Register.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        Register register = query.getSingleResult();
        return register != null;
    }

    @Override
    public boolean activation(byte code) {

        return false;
    }
}

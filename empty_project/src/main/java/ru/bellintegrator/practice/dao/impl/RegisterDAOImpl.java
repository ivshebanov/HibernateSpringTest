package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.RegisterDAO;

import javax.persistence.EntityManager;

@Repository
public class RegisterDAOImpl implements RegisterDAO {

    private final EntityManager em;

    @Autowired
    public RegisterDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean register(String login, String password, String name) {
        return false;
    }

    @Override
    public boolean login(String login, String password) {
        return false;
    }

    @Override
    public boolean activation(byte code) {
        return false;
    }
}

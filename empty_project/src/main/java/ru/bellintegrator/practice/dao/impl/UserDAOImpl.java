package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> all(int officeId) {
        return null;
    }

    @Override
    public User loadUserById(Long id) {
        return null;
    }

    @Override
    public boolean updateUserById(long id, User user) {
        return false;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }

    @Override
    public boolean save(User user) {
        return false;
    }
}

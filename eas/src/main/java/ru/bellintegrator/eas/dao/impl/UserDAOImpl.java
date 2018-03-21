package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Transactional
    @Override
    public List<User> all(long officeId) throws MyException {
        if (officeId <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid officeId : ").
                    append("officeId = ").append(officeId);
            throw new MyException(sb.toString());
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> userRoot = criteria.from(User.class);
            criteria.where(builder.equal(userRoot.get("officeId"), officeId));
            TypedQuery<User> query = em.createQuery(criteria);
            List<User> users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public User load(long id) throws MyException {
        if (id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public boolean update(long id, User user) throws MyException {
        if (id <= 0L || user == null) {
            StringBuilder sb = new StringBuilder("Invalid id or user: ").
                    append("id = ").append(id).
                    append(", user = ").append(user);
            throw new MyException(sb.toString());
        }
        try {
            user.setId(id);
            em.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean delete(long id) throws MyException {
        if (id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        try {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean save(User user) throws MyException {
        if (user == null) {
            StringBuilder sb = new StringBuilder("Invalid user : ").
                    append("user = ").append(user);
            throw new MyException(sb.toString());
        }
        try {
            if (user.getId() == 0) {
                em.persist(user);
                return true;
            } else {
                update(user.getId(), user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}

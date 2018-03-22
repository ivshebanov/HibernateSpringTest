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
    public List<User> all(Long officeId) throws MyException {
        if (officeId == null || officeId <= 0L) {
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
            throw new MyException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public User load(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public boolean update(Long id, User user) throws MyException {
        if (id == null || id <= 0L || user == null) {
            StringBuilder sb = new StringBuilder("Invalid id or user: ").
                    append("id = ").append(id).
                    append(", user = ").append(user);
            throw new MyException(sb.toString());
        }
        try {
            user.setId(id);
            em.merge(user);
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
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            return true;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
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
//            throw new MyException(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

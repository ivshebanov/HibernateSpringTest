package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    public List<User> all(long officeId) {
        if (officeId <= 0L) {
            return null;
        }
        try {
//            String queryString = "SELECT h FROM " + User.class.getSimpleName() +
//                    " h WHERE h.officeId = :officeId";
//            System.out.println(queryString);
//            TypedQuery<User> query = em.createQuery(queryString, User.class);
//            query.setParameter("officeId", officeId);
//            List<User> allUsers = query.getResultList();
//            return allUsers;

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> userRoot = criteria.from(User.class);
            criteria.where(builder.equal(userRoot.get("officeId"), officeId));
            TypedQuery<User> query = em.createQuery(criteria);
            System.out.println(query.getResultList());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public User load(long id) {
        if (id <= 0L) {
            return null;
        }
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public boolean update(long id, User user) {
        if (id <= 0L || user == null) {
            return false;
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
    public boolean delete(long id) {
        if (id <= 0L) {
            return false;
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
    public boolean save(User user) {
        if (user == null) {
            return false;
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

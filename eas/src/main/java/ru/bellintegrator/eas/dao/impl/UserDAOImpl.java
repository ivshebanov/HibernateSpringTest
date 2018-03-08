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
    public List<User> all(int officeId) {
        if (officeId <= 0) {
            return null;
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> officeRoot = criteria.from(User.class);
            criteria.where(builder.equal(officeRoot.get("officeId"), officeId));
            TypedQuery<User> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public User load(Long id) {
        if (id <= 0) {
            return null;
        }
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public boolean update(long id, User user) {
        if (id <= 0 || user == null) {
            return false;
        }
        try {
            User oldUser = load(id);
            oldUser.setFirstName(user.getFirstName());
            oldUser.setSecondName(user.getSecondName());
            oldUser.setMiddleName(user.getMiddleName());
            oldUser.setPosition(user.getPosition());
            oldUser.setPhone(user.getPhone());
            oldUser.setDocumentations(user.getDocumentations());
            oldUser.setOfficeId(user.getOfficeId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id <= 0) {
            return false;
        }
        try {
            em.remove(load(id));
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
            if (user.getId() == null) {
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

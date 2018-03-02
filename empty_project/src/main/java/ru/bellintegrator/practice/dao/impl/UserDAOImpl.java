package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        String queryString = "SELECT u FROM User u WHERE u.officeId = :officeId";
        TypedQuery<User> query = em.createQuery(queryString, User.class);
        query.setParameter("officeId", officeId);
        return query.getResultList();
    }

    @Override
    public User load(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean update(long id, User user) {
        try {
            User oldUser = load(id);
            em.getTransaction().begin();
            oldUser.setFirstName(user.getFirstName());
            oldUser.setSecondName(user.getSecondName());
            oldUser.setMiddleName(user.getMiddleName());
            oldUser.setPosition(user.getPosition());
            oldUser.setPhone(user.getPhone());
            oldUser.setDocumentations(user.getDocumentations());
            oldUser.setOfficeId(user.getOfficeId());
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            em.getTransaction().begin();
            em.remove(load(id));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public boolean save(User user) {
        try {
            em.getTransaction().begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                update(user.getId(), user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}

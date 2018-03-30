package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.Country;
import ru.bellintegrator.eas.model.Doc;
import ru.bellintegrator.eas.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> all(Long officeId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(builder.equal(userRoot.get("officeId"), officeId));
        TypedQuery<User> query = em.createQuery(criteria);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            return new ArrayList<>();
        }
        return users;
    }

    public List<User> loadUser(Long officeId, String firstName, String secondName, String middleName,
                               String position, int docCode, int citizenshipCode) {
        //
        return new ArrayList<>();
    }

    @Override
    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean update(Long id, User user, int docCode, String docName,
                          int citizenshipCode, String citizenshipName) throws MyException {
        User userResult = em.find(User.class, id);
        userResult.setId(user.getId());
        userResult.setFirstName(user.getFirstName());
        userResult.setSecondName(user.getSecondName());
        userResult.setMiddleName(user.getMiddleName());
        userResult.setPosition(user.getPosition());
        userResult.setPhone(user.getPhone());
        userResult.setDocNumber(user.getDocNumber());
        userResult.setDocDate(user.getDocDate());
        userResult.setIdentified(user.isIdentified());
        userResult.setDoc(checkDoc(docCode, docName));
        userResult.setCountry(checkCountry(citizenshipCode, citizenshipName));
        em.merge(user);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
        return true;
    }

    @Override
    public boolean save(User user, int docCode, String docName,
                        int citizenshipCode, String citizenshipName) throws MyException {
        if (user.getId() == null) {
            user.setDoc(checkDoc(docCode, docName));
            user.setCountry(checkCountry(citizenshipCode, citizenshipName));
            em.persist(user);
        } else {
            update(user.getId(), user, docCode, docName, citizenshipCode, citizenshipName);
        }
        return true;
    }

    private Doc checkDoc(int docCode, String docName) throws MyException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Doc> criteria = cb.createQuery(Doc.class);
        Root<Doc> docRoot = criteria.from(Doc.class);
        criteria.where(cb.equal(docRoot.get("code"), docCode));
        TypedQuery<Doc> query = em.createQuery(criteria);
        List<Doc> docs = query.getResultList();

        if (docs == null || docs.size() == 0) {
            Query docQuery = em.createNativeQuery("INSERT INTO DOC (CODE, DOC_NAME) " +
                    "VALUES (?,?)");
            docQuery.setParameter(1, docCode);
            docQuery.setParameter(2, docName);
            if (query.executeUpdate() != 1) {
                throw new MyException("Ошибка добавления Doc");
            }
        }
        return query.getSingleResult();
    }

    private Country checkCountry(int citizenshipCode, String citizenshipName) throws MyException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = cb.createQuery(Country.class);
        Root<Country> docRoot = criteria.from(Country.class);
        criteria.where(cb.equal(docRoot.get("code"), citizenshipCode));
        TypedQuery<Country> query = em.createQuery(criteria);
        List<Country> countries = query.getResultList();

        if (countries == null || countries.size() == 0) {
            Query countryQuery = em.createNativeQuery("INSERT INTO DOC (CODE, CITIZENSHIP_NAME) " +
                    "VALUES (?,?)");
            countryQuery.setParameter(1, citizenshipCode);
            countryQuery.setParameter(2, citizenshipName);
            if (query.executeUpdate() != 1) {
                throw new MyException("Ошибка добавления Country");
            }
        }
        return query.getSingleResult();
    }
}

package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.CountryDAO;
import ru.bellintegrator.eas.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO {

    private final EntityManager em;

    @Autowired
    public CountryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public List<Country> all() throws MyException {
        try {
            String sqlQuery = "SELECT o FROM Country o";
            TypedQuery<Country> query =
                    em.createQuery(sqlQuery, Country.class);
            List<Country> countries = query.getResultList();
            if (countries.isEmpty()) {
                throw new MyException("Countries directory is empty");
            }
            return countries;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}

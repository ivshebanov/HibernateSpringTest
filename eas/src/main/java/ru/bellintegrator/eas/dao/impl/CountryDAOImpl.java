package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.CountryDAO;
import ru.bellintegrator.eas.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Country> criteriaQuery = cb.createQuery(Country.class);
            Root<Country> countryRoot = criteriaQuery.from(Country.class);
            criteriaQuery.select(countryRoot);
            TypedQuery<Country> query = em.createQuery(criteriaQuery);
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

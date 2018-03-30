package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.dao.CountryDAO;
import ru.bellintegrator.eas.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDAOImpl implements CountryDAO {

    private final EntityManager em;

    @Autowired
    public CountryDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Country> all() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = cb.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(countryRoot);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        List<Country> countries = query.getResultList();
        if (countries.isEmpty()) {
            return new ArrayList<>();
        }
        return countries;
    }
}

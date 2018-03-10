package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.dao.OfficeDAO;
import ru.bellintegrator.eas.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public List<Office> all(long orgId) {
        if (orgId <= 0L) {
            return null;
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
            Root<Office> officeRoot = criteria.from(Office.class);
            criteria.where(builder.equal(officeRoot.get("orgId"), orgId));
            TypedQuery<Office> query = em.createQuery(criteria);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Office load(long id) {
        if (id <= 0L) {
            return null;
        }
        return em.find(Office.class, id);
    }

    @Transactional
    @Override
    public boolean update(long id, Office office) {
        if (id <= 0L || office == null) {
            return false;
        }
        try {
            office.setId(id);
            em.merge(office);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean delete(long id) {
        if (id <= 0L) {
            return false;
        }
        try {
            em.remove(load(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean save(Office office) {
        if (office == null) {
            return false;
        }
        try {
            if (office.getId() == 0) {
                em.persist(office);
            } else {
                update(office.getId(), office);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.dao.OfficeDAO;
import ru.bellintegrator.eas.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> all(Long orgId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteria.from(Office.class);
        criteria.where(builder.equal(officeRoot.get("orgId"), orgId));
        TypedQuery<Office> query = em.createQuery(criteria);
        List<Office> offices = query.getResultList();
        if (offices.isEmpty()) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }

    @Override
    public List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive) {
        //реализовать
        return new ArrayList<>();
    }

    @Override
    public Office loadById(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public boolean update(Long id, Office office) {
        Office officeResult = em.find(Office.class, id);
        officeResult.setId(office.getId());
        officeResult.setName(office.getName());
        officeResult.setAddress(office.getAddress());
        officeResult.setPhone(office.getPhone());
        officeResult.setActive(office.isActive());
        em.merge(officeResult);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Office office = em.find(Office.class, id);
        if (office != null) {
            em.remove(office);
        }
        return true;
    }

    @Override
    public boolean save(Office office) {
        if (office.getId() == null) {
            em.persist(office);
        } else {
            update(office.getId(), office);
        }
        return true;
    }
}

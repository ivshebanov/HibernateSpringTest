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
    public List<Office> all(int orgId) {
        if (orgId <= 0) {
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
    public Office load(Long id) {
        if (id <= 0) {
            return null;
        }
        return em.find(Office.class, id);
    }

    @Transactional
    @Override
    public boolean update(long id, Office office) {
        if (id <= 0 || office == null) {
            return false;
        }
        try {
            Office oldOffice = load(id);
            oldOffice.setName(office.getName());
            oldOffice.setPhone(office.getPhone());
            oldOffice.setAddress(office.getAddress());
            oldOffice.setActive(office.isActive());
            oldOffice.setUsers(office.getUsers());
            oldOffice.setOrgId(office.getOrgId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id <= 0) {
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
            if (office.getId() == null) {
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

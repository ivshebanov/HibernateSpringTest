package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.model.Office;

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

    @Override
    public List<Office> all(int orgId) {
        if (orgId < 0) {
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
        }

//        String queryString = "SELECT o FROM Office o WHERE o.orgId = :orgId";
//        TypedQuery<Office> query = em.createQuery(queryString, Office.class);
//        query.setParameter("orgId", orgId);
        return null;
    }

    @Override
    public Office load(Long id) {
        return em.find(Office.class, id);
    }

    @Override
    public boolean update(long id, Office office) {
        try {
            Office oldOffice = load(id);
            em.getTransaction().begin();
            oldOffice.setName(office.getName());
            oldOffice.setPhone(office.getPhone());
            oldOffice.setAddress(office.getAddress());
            oldOffice.setActive(office.isActive());
            oldOffice.setUsers(office.getUsers());
            oldOffice.setOrgId(office.getOrgId());
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
    public boolean save(Office office) {
        try {
            em.getTransaction().begin();
            if (office.getId() == null) {
                em.persist(office);
            } else {
                update(office.getId(), office);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}

package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
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
    public List<Office> all(Long orgId) throws MyException {
        if (orgId == null || orgId <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid orgId : ").
                    append("orgId = ").append(orgId);
            throw new MyException(sb.toString());
        }
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Office> criteria = builder.createQuery(Office.class);
            Root<Office> officeRoot = criteria.from(Office.class);
            criteria.where(builder.equal(officeRoot.get("orgId"), orgId));
            TypedQuery<Office> query = em.createQuery(criteria);
            List<Office> offices = query.getResultList();
            if (offices.isEmpty()) {
                return null;
            }
            return query.getResultList();
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive) throws MyException {
        if (orgId == null || orgId <= 0L || name == null || phone < 0) {
            StringBuilder sb = new StringBuilder("Invalid orgId, name or phone: ").
                    append("orgId = ").append(orgId).
                    append("name = ").append(name).
                    append("phone = ").append(phone);
            throw new MyException(sb.toString());
        }
        //реализовать
        return null;
    }

    @Transactional
    @Override
    public Office loadById(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        return em.find(Office.class, id);
    }

    @Transactional
    @Override
    public boolean update(Long id, Office office) throws MyException {
        if (id == null || id <= 0L || office == null) {
            StringBuilder sb = new StringBuilder("Invalid id or office: ").
                    append("id = ").append(id).
                    append(", office = ").append(office);
            throw new MyException(sb.toString());
        }
        office.setId(id);
        em.merge(office);
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws MyException {
        if (id == null || id <= 0L) {
            StringBuilder sb = new StringBuilder("Invalid id : ").
                    append("id = ").append(id);
            throw new MyException(sb.toString());
        }
        Office office = em.find(Office.class, id);
        if (office != null) {
            em.remove(office);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean save(Office office) throws MyException {
        if (office == null) {
            StringBuilder sb = new StringBuilder("Invalid office : ").
                    append("office = ").append(office);
            throw new MyException(sb.toString());
        }
        if (office.getId() == null) {
            em.persist(office);
        } else {
            update(office.getId(), office);
        }
        return true;
    }
}

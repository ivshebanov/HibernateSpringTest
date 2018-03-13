package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private final EntityManager em;

    @Autowired
    public OrganizationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public List<Organization> all() {
        try {
            String sqlQuery = "SELECT o FROM Organization o";
            TypedQuery<Organization> query =
                    em.createQuery(sqlQuery, Organization.class);
            List<Organization> organizations = query.getResultList();
            if (organizations.isEmpty()) {
                return null;
            }
            return organizations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Organization load(long id) {
        if (id <= 0L) {
            return null;
        }
        try {
            Organization organization = em.find(Organization.class, id);
            if (organization.isActive()) {
                return organization;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public boolean update(long id, Organization organization) {
        if (id <= 0L || organization == null) {
            return false;
        }
        try {
            organization.setId(id);
            em.merge(organization);
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
            Organization organization = em.find(Organization.class, id);
            if (organization != null) {
                em.remove(organization); //не работает
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean save(Organization organization) {
        if (organization == null) {
            return false;
        }
        try {
            if (organization.getId() == 0) {
                em.persist(organization);
            } else {
                update(organization.getId(), organization);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

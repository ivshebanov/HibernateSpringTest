package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;

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
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public Organization load(Long id) {
        if (id <= 0) {
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
        if (id <= 0 || organization == null) {
            return false;
        }
        try {
            Organization oldOrganization = load(id);
            oldOrganization.setName(organization.getName());
            oldOrganization.setFullName(organization.getFullName());
            oldOrganization.setInn(organization.getInn());
            oldOrganization.setKpp(organization.getKpp());
            oldOrganization.setAddress(organization.getAddress());
            oldOrganization.setPhone(organization.getPhone());
            oldOrganization.setActive(organization.isActive());
            oldOrganization.setOffices(organization.getOffices());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        if (id <= 0) {
            return false;
        }
        try {
            em.remove(load(id));
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
            if (organization.getId() == null) {
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

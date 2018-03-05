package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Organization;

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
        TypedQuery<Organization> query =
                em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Organization load(Long id) {
        if (id < 1){
            return null;
        }
        Organization organization = em.find(Organization.class, id);
        if (organization.isActive()) {
            return organization;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean update(long id, Organization organization) {
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
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        try {
            em.remove(load(id));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean save(Organization organization) {
        try {
            if (organization.getId() == null) {
                em.persist(organization);
            } else {
                update(organization.getId(), organization);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

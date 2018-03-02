package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query =
                em.createQuery("SELECT o FROM Organization o", Organization.class);
        return query.getResultList();
    }

    @Override
    public Organization load(Long id) {
        Organization organization = em.find(Organization.class, id);
        if (organization.isActive()) {
            return organization;
        }
        return null;
    }

    @Override
    public boolean update(long id, Organization organization) {
        try {
            Organization oldOrganization = load(id);
            em.getTransaction().begin();
            oldOrganization.setName(organization.getName());
            oldOrganization.setFullName(organization.getFullName());
            oldOrganization.setInn(organization.getInn());
            oldOrganization.setKpp(organization.getKpp());
            oldOrganization.setAddress(organization.getAddress());
            oldOrganization.setPhone(organization.getPhone());
            oldOrganization.setActive(organization.isActive());
            oldOrganization.setOffices(organization.getOffices());
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
    public boolean save(Organization organization) {
        try {
            em.getTransaction().begin();
            if (organization.getId() == null) {
                em.persist(organization);
            } else {
                update(organization.getId(), organization);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }
}

package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
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
        return null;
    }

    @Override
    public Organization loadOrganizationById(Long id) {
        return null;
    }

    @Override
    public boolean updateOrganizationById(long id, Organization organization) {
        return false;
    }

    @Override
    public boolean deleteOrganizationById(Long id) {
        return false;
    }

    @Override
    public boolean save(Organization organization) {
        return false;
    }
}

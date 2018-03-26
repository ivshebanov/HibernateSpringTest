package ru.bellintegrator.eas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.service.OrganizationService;
import ru.bellintegrator.eas.view.OrganizationView;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @Override
    @Transactional
    public List<Organization> loadOrganization(String name, OrganizationView organizationView) {
        log.debug("loadOrganization: name = " + name + ", organizationView = " + organizationView.toString());
        try {
            return organizationDAO.loadOrganization(name, organizationView.getInn(), organizationView.isActive());
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public Organization loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            return organizationDAO.loadById(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(OrganizationView organizationView) {
        log.debug("update: organizationView = " + organizationView.toString());
        try {
            Long id = Long.parseLong(organizationView.getId());
            Organization organization = new Organization();
            organization.setId(id);
            organization.setName(organizationView.getName());
            organization.setFullName(organizationView.getFullName());
            organization.setInn(organizationView.getInn());
            organization.setKpp(organizationView.getKpp());
            organization.setAddress(organizationView.getAddress());
            organization.setPhone(organizationView.getPhone());
            organization.setActive(organizationView.isActive());
            return organizationDAO.update(id, organization);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        log.debug("delete: id = " + id);
        try {
            return organizationDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(OrganizationView organizationView) {
        log.debug("save: organizationView = " + organizationView.toString());
        try {
            Organization organization = new Organization();
            organization.setName(organizationView.getName());
            organization.setFullName(organizationView.getFullName());
            organization.setInn(organizationView.getInn());
            organization.setKpp(organizationView.getKpp());
            organization.setAddress(organizationView.getAddress());
            organization.setPhone(organizationView.getPhone());
            organization.setActive(organizationView.isActive());
            return organizationDAO.save(organization);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }
}

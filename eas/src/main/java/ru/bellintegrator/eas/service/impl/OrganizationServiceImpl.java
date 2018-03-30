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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    public List<Organization> loadOrganization(@NotNull String name, int inn, boolean isActive) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("loadOrganization :").
                append(" name =").append(name).
                append(", inn =").append(inn).
                append(", isActive =").append(isActive);
        log.debug(stringBuilder.toString());
        try {
            if (inn <= 0) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append(" name =").append(name).
                        append(", inn =").append(inn).
                        append(", isActive =").append(isActive);
                throw new MyException(sb.toString());
            }
            return organizationDAO.loadOrganization(name, inn, isActive);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public Organization loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            if (id == null || id <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid id : ").
                        append("id = ").append(id);
                throw new MyException(sb.toString());
            }
            return organizationDAO.loadById(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(@Valid OrganizationView organizationView) {
        log.debug("update: organizationView = " + organizationView.toString());
        try {
            if (organizationView.getId() == null
                    || organizationView.getId().isEmpty()
                    || Long.parseLong(organizationView.getId()) <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append(", organizationView = ").append(organizationView);
                throw new MyException(sb.toString());
            }
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
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        log.debug("delete: id = " + id);
        try {
            if (id == null || id <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid id : ").
                        append("id = ").append(id);
                throw new MyException(sb.toString());
            }
            return organizationDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(@Valid OrganizationView organizationView) {
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
        } catch (Exception e) {
            log.error("MyException error", e);
        }
        return false;
    }
}

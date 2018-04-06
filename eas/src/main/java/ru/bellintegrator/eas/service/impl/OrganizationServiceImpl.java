package ru.bellintegrator.eas.service.impl;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.exception.MyException;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.service.OrganizationService;
import ru.bellintegrator.eas.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    private final OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @Override
    @Transactional
    public List<OrganizationView> loadOrganization(String name, int inn, boolean isActive) {
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
            List<Organization> organizations = organizationDAO.loadOrganization(name, inn, isActive);
            return mapOrganizationListToOrganizationViewList(organizations);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public OrganizationView loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            if (id == null || id <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid id : ").
                        append("id = ").append(id);
                throw new MyException(sb.toString());
            }
            Organization organization = organizationDAO.loadById(id);
            return mapOrganizationToOrganizationView(organization);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(OrganizationView organizationView) {
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
            Organization organization = mapOrganizationViewToOrganization(organizationView);
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
    public boolean save(OrganizationView organizationView) {
        log.debug("save: organizationView = " + organizationView.toString());
        try {
            Organization organization = mapOrganizationViewToOrganization(organizationView);
            return organizationDAO.save(organization);
        } catch (Exception e) {
            log.error("MyException error", e);
        }
        return false;
    }

    private List<OrganizationView> mapOrganizationListToOrganizationViewList(List<Organization> organizationList) {
        List<OrganizationView> organizationViews = new ArrayList<>(organizationList.size());
        for (Organization organization : organizationList) {
            organizationViews.add(mapOrganizationToOrganizationView(organization));
        }
        return organizationViews;
    }

    private OrganizationView mapOrganizationToOrganizationView(Organization organization) {
        mapperFactory.classMap(Organization.class, OrganizationView.class).byDefault()
                .exclude("version").exclude("offices").byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(organization, OrganizationView.class);
    }

    private Organization mapOrganizationViewToOrganization(OrganizationView organizationView) {
        mapperFactory.classMap(Organization.class, OrganizationView.class).byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(organizationView, Organization.class);
    }
}

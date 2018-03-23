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
    public List<Organization> loadOrganization(String name, int inn, boolean isActive) throws MyException {
        return null;
    }

    @Override
    @Transactional
    public Organization loadById(Long id) throws MyException {
        return null;
    }

    @Override
    @Transactional
    public boolean update(Long id, String name, String fullName, int inn, int kpp,
                          String address, int phone, boolean isActive) throws MyException {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws MyException {
        return false;
    }

    @Override
    @Transactional
    public boolean save(String name, String fullName, int inn, int kpp,
                        String address, int phone, boolean isActive) throws MyException {
        return false;
    }
}

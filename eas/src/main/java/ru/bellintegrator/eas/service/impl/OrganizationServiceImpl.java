package ru.bellintegrator.eas.service.impl;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Organization;
import ru.bellintegrator.eas.service.OrganizationService;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public List<Organization> loadOrganization(String name, int inn, boolean isActive) throws MyException {
        return null;
    }

    @Override
    public Organization loadById(Long id) throws MyException {
        return null;
    }

    @Override
    public boolean update(Long id, String name, String fullName, int inn, int kpp,
                          String address, int phone, boolean isActive) throws MyException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws MyException {
        return false;
    }

    @Override
    public boolean save(String name, String fullName, int inn, int kpp,
                        String address, int phone, boolean isActive) throws MyException {
        return false;
    }
}

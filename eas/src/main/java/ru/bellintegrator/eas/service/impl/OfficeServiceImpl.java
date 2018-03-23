package ru.bellintegrator.eas.service.impl;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.service.OfficeService;

import java.util.List;

public class OfficeServiceImpl implements OfficeService {

    @Override
    public List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive) throws MyException {
        return null;
    }

    @Override
    public Office loadById(Long id) throws MyException {
        return null;
    }

    @Override
    public boolean update(Long id, String name, String address, int phone, boolean isActive) throws MyException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws MyException {
        return false;
    }

    @Override
    public boolean save(String name, String address, int phone, boolean isActive) throws MyException {
        return false;
    }
}

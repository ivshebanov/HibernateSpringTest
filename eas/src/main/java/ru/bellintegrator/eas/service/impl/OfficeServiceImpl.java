package ru.bellintegrator.eas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.OfficeDAO;
import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.service.OfficeService;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDAO officeDAO;

    @Autowired
    public OfficeServiceImpl(OfficeDAO officeDAO) {
        this.officeDAO = officeDAO;
    }

    @Override
    @Transactional
    public List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive) throws MyException {
        return null;
    }

    @Override
    @Transactional
    public Office loadById(Long id) throws MyException {
        return null;
    }

    @Override
    @Transactional
    public boolean update(Long id, String name, String address, int phone, boolean isActive) throws MyException {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws MyException {
        return false;
    }

    @Override
    @Transactional
    public boolean save(String name, String address, int phone, boolean isActive) throws MyException {
        return false;
    }
}

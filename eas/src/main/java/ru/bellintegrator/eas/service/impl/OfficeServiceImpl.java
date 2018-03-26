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
import ru.bellintegrator.eas.view.OfficeView;

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
    public List<Office> loadOffice(Long orgId, OfficeView officeView) {
        log.debug("loadOffice: orgId = " + orgId + ", officeView = " + officeView.toString());
        try {
            return officeDAO.loadOffice(orgId, officeView.getName(), officeView.getPhone(), officeView.isActive());
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public Office loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            return officeDAO.loadById(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(OfficeView officeView) {
        log.debug("update: officeView = " + officeView.toString());
        try {
            Long id = Long.parseLong(officeView.getId());
            Office office = new Office();
            office.setId(id);
            office.setName(officeView.getName());
            office.setAddress(officeView.getAddress());
            office.setPhone(officeView.getPhone());
            office.setActive(officeView.isActive());
            return officeDAO.update(id, office);
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
            return officeDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(OfficeView officeView) {
        log.debug("save: officeView = " + officeView.toString());
        try {
            Office office = new Office();
            office.setName(officeView.getName());
            office.setAddress(officeView.getAddress());
            office.setPhone(officeView.getPhone());
            office.setActive(officeView.isActive());
            return officeDAO.save(office);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }
}

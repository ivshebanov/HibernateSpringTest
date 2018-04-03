package ru.bellintegrator.eas.service.impl;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
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
import ru.bellintegrator.eas.service.impl.mapper.OfficeCustomMapper;
import ru.bellintegrator.eas.view.OfficeView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final CustomMapper<Office, OfficeView> customMapper = new OfficeCustomMapper();


    private final OfficeDAO officeDAO;

    @Autowired
    public OfficeServiceImpl(OfficeDAO officeDAO) {
        this.officeDAO = officeDAO;
    }

    @Override
    @Transactional
    public List<OfficeView> loadOffice(@NotNull Long orgId, String name, int phone, boolean isActive) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("loadOffice :").
                append(" orgId = ").append(orgId).
                append(", name = ").append(name).
                append(", phone = ").append(phone).
                append(", isActive = ").append(isActive);
        log.debug(stringBuilder.toString());
        try {
            if (orgId <= 0L || phone <= 0) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append(" orgId = ").append(orgId).
                        append(", name = ").append(name).
                        append(", phone = ").append(phone).
                        append(", isActive = ").append(isActive);
                throw new MyException(sb.toString());
            }

            List<Office> officeList = officeDAO.loadOffice(orgId, name, phone, isActive);
            return mapOfficeListToOfficeViewList(officeList);

        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public OfficeView loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            if (id <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid id : ").
                        append("id = ").append(id);
                throw new MyException(sb.toString());
            }

            Office office = officeDAO.loadById(id);
            return mapOfficeToOfficeView(office);

        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(@Valid OfficeView officeView) {
        log.debug("update: officeView = " + officeView.toString());
        try {
            if (officeView.getId() == null
                    || officeView.getId().isEmpty()
                    || Long.parseLong(officeView.getId()) <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append("officeView = ").append(officeView);
                throw new MyException(sb.toString());
            }
            Long id = Long.parseLong(officeView.getId());
            Office office = mapOfficeViewToOffice(officeView);
            return officeDAO.update(id, office);
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
            return officeDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(@Valid OfficeView officeView) {
        log.debug("save: officeView = " + officeView.toString());
        try {
            Office office = mapOfficeViewToOffice(officeView);
            return officeDAO.save(office);
        } catch (Exception e) {
            log.error("MyException error", e);
        }
        return false;
    }


    private List<OfficeView> mapOfficeListToOfficeViewList(List<Office> listOffice) {
        List<OfficeView> officeViewList = new ArrayList<>(listOffice.size());
        for (Office office : listOffice) {
            officeViewList.add(mapOfficeToOfficeView(office));
        }
        return officeViewList;
    }

    private OfficeView mapOfficeToOfficeView(Office office) {
        mapperFactory.classMap(Office.class, OfficeView.class).customize(customMapper)
                .exclude("version").exclude("orgId").exclude("users").register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(office, OfficeView.class);
    }

    private Office mapOfficeViewToOffice(OfficeView officeView) {
        mapperFactory.classMap(Office.class, OfficeView.class).customize(customMapper).register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(officeView, Office.class);
    }
}
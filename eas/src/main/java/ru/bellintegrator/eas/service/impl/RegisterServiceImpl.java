package ru.bellintegrator.eas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.exception.MyException;
import ru.bellintegrator.eas.dao.OrganizationDAO;
import ru.bellintegrator.eas.service.RegisterService;

import java.security.NoSuchAlgorithmException;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class RegisterServiceImpl implements RegisterService {

    private final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private final OrganizationDAO organizationDAO;

    @Autowired
    public RegisterServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @Override
    @Transactional
    public boolean register(String login, String password, String name) {
        log.debug("register: login = " + login + ", name = " + name);
        try {
            if (login.isEmpty() || password.isEmpty() || name.isEmpty()) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append("login = ").append(login).
                        append(", password = ").append("Invalid").
                        append(", name = ").append(name);
                throw new MyException(sb.toString());
            }
            organizationDAO.register(login, password, name);
            return true;
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean login(String login, String password) {
        log.debug("login: login = " + login);
        try {
            if (login.isEmpty() || password.isEmpty()) {
                StringBuilder sb = new StringBuilder("Invalid parameters : ").
                        append("login = ").append(login).
                        append(", password = ").append("Invalid");
                throw new MyException(sb.toString());
            }
            organizationDAO.login(login, password);
            return true;
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean activation(String hashCode) {
        log.debug("activation: hashCode = " + hashCode);
        try {
            if (hashCode.isEmpty()) {
                StringBuilder sb = new StringBuilder("Invalid hashCode : ").
                        append("hashCode = ").append(hashCode);
                throw new MyException(sb.toString());
            }
            organizationDAO.activation(hashCode);
            return true;
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }
}

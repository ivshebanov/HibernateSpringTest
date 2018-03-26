package ru.bellintegrator.eas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.service.UserService;
import ru.bellintegrator.eas.view.UserView;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> loadUser(Long officeId, UserView userView) {
        log.debug("loadUser: officeId = " + officeId + ", userView = " + userView.toString());
        try {
            return userDAO.loadUser(officeId, userView.getFirstName(),
                    userView.getSecondName(), userView.getMiddleName(),
                    userView.getPosition(), userView.getDocCode(),
                    userView.getCitizenshipCode());
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public User loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            return userDAO.loadById(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(UserView userView) {
        log.debug("update: userView = " + userView.toString());
        try {
            Long id = Long.parseLong(userView.getId());
            User user = new User();
            user.setId(id);
            user.setFirstName(userView.getFirstName());
            user.setSecondName(userView.getSecondName());
            user.setMiddleName(userView.getMiddleName());
            user.setPosition(userView.getPosition());
            user.setPhone(userView.getPhone());
            user.setDocNumber(userView.getDocNumber());
            user.setDocDate(userView.getDocDate());
            user.setIdentified(userView.isIdentified());
            return userDAO.update(id, user, userView.getDocCode(), userView.getDocName(),
                    userView.getCitizenshipCode(), userView.getCitizenshipName());
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
            return userDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(UserView userView) {
        log.debug("save: userView = " + userView.toString());
        try {
            User user = new User();
            user.setFirstName(userView.getFirstName());
            user.setSecondName(userView.getSecondName());
            user.setMiddleName(userView.getMiddleName());
            user.setPosition(userView.getPosition());
            user.setPhone(userView.getPhone());
            user.setDocNumber(userView.getDocNumber());
            user.setDocDate(userView.getDocDate());
            user.setIdentified(userView.isIdentified());
            return userDAO.save(user, userView.getDocCode(), userView.getDocName(),
                    userView.getCitizenshipCode(), userView.getCitizenshipName());
        } catch (MyException e) {
            log.error("MyException error", e);
        }
        return false;
    }
}

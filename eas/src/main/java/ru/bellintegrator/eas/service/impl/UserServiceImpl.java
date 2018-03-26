package ru.bellintegrator.eas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return null;
    }

    @Override
    @Transactional
    public User loadById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public boolean update(UserView userView) {
        return false;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @Transactional
    public boolean save(UserView userView) {
        return false;
    }
}

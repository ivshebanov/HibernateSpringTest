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
import ru.bellintegrator.eas.dao.UserDAO;
import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.service.UserService;
import ru.bellintegrator.eas.service.impl.mapper.UserCustomMapper;
import ru.bellintegrator.eas.view.UserView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final CustomMapper<User, UserView> customMapper = new UserCustomMapper();

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<UserView> loadUser(@NotNull Long officeId, String firstName, String secondName, String middleName,
                                   String position, int docCode, int citizenshipCode) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("loadUser :").
                append(" officeId = ").append(officeId).
                append(", firstName = ").append(firstName).
                append(", secondName = ").append(secondName).
                append(", middleName = ").append(middleName).
                append(", position = ").append(position).
                append(", docCode = ").append(docCode).
                append(", citizenshipCode = ").append(citizenshipCode);
        log.debug(stringBuilder.toString());
        try {
            if (officeId <= 0L || docCode <= 0 || citizenshipCode <= 0) {
                StringBuilder sb = new StringBuilder("Invalid parameter: ").
                        append(" officeId = ").append(officeId).
                        append(", firstName = ").append(firstName).
                        append(", secondName = ").append(secondName).
                        append(", middleName = ").append(middleName).
                        append(", position = ").append(position).
                        append(", docCode = ").append(docCode).
                        append(", citizenshipCode = ").append(citizenshipCode);
                throw new MyException(sb.toString());
            }
            List<User> users = userDAO.loadUser(officeId, firstName, secondName, middleName, position, docCode, citizenshipCode);
            return mapUserListToUserViewList(users);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public UserView loadById(Long id) {
        log.debug("loadById: id = " + id);
        try {
            if (id == null || id <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid id : ").
                        append("id = ").append(id);
                throw new MyException(sb.toString());
            }
            User user = userDAO.loadById(id);
            return mapUserToUserView(user);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(@Valid UserView userView) {
        log.debug("update: userView = " + userView.toString());
        try {
            if (userView.getId() == null
                    || userView.getId().isEmpty()
                    || Long.parseLong(userView.getId()) <= 0L) {
                StringBuilder sb = new StringBuilder("Invalid parameter: ").
                        append(", userView = ").append(userView);
                throw new MyException(sb.toString());
            }
            Long id = Long.parseLong(userView.getId());
            User user = mapUserViewToUser(userView);
            return userDAO.update(id, user, userView.getDocCode(), userView.getDocName(),
                    userView.getCitizenshipCode(), userView.getCitizenshipName());
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
            return userDAO.delete(id);
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    @Override
    @Transactional
    public boolean save(@Valid UserView userView) {
        log.debug("save: userView = " + userView.toString());
        try {
            User user = mapUserViewToUser(userView);
            return userDAO.save(user, userView.getDocCode(), userView.getDocName(),
                    userView.getCitizenshipCode(), userView.getCitizenshipName());
        } catch (MyException e) {
            log.error("MyException error", e);
        } catch (Exception e) {
            log.error("Exception error", e);
        }
        return false;
    }

    private List<UserView> mapUserListToUserViewList(List<User> userList) {
        List<UserView> userViews = new ArrayList<>(userList.size());
        for (User user : userList) {
            userViews.add(mapUserToUserView(user));
        }
        return userViews;
    }

    private UserView mapUserToUserView(User user) {
        mapperFactory.classMap(User.class, UserView.class).customize(customMapper)
                .exclude("version").exclude("doc").exclude("country").exclude("officeId").register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(user, UserView.class);
    }

    private User mapUserViewToUser(UserView userView) {
        mapperFactory.classMap(User.class, UserView.class).customize(customMapper)
                .exclude("docCode").exclude("docName").exclude("citizenshipCode").exclude("citizenshipName").register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(userView, User.class);
    }
}

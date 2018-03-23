package ru.bellintegrator.eas.service.impl;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.service.UserService;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> loadUser(Long officeId, String firstName, String secondName, String middleName,
                               String position, int docCode, int citizenshipCode) throws MyException {
        return null;
    }

    @Override
    public User loadById(Long id) throws MyException {
        return null;
    }

    @Override
    public boolean update(Long id, String firstName, String secondName, String middleName, String position,
                          int phone, int docCode, int docNumber, Date docDate, int citizenshipCode,
                          String citizenshipName, boolean isIdentified) throws MyException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws MyException {
        return false;
    }

    @Override
    public boolean save(String firstName, String secondName, String middleName, String position,
                        int phone, int docCode, int docNumber, Date docDate, int citizenshipCode,
                        String citizenshipName, boolean isIdentified) throws MyException {
        return false;
    }
}

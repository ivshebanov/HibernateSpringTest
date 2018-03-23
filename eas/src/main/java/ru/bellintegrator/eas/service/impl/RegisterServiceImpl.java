package ru.bellintegrator.eas.service.impl;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.service.RegisterService;

import java.security.NoSuchAlgorithmException;

public class RegisterServiceImpl implements RegisterService {

    @Override
    public boolean register(String login, String password, String name) throws MyException, NoSuchAlgorithmException {
        return false;
    }

    @Override
    public boolean login(String login, String password) throws MyException, NoSuchAlgorithmException {
        return false;
    }

    @Override
    public boolean activation(String hashCode) throws MyException {
        return false;
    }
}

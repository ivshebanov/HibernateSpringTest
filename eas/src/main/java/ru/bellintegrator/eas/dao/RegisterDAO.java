package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;

public interface RegisterDAO {

    /**
     * Регистрация новой организации
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @param name     краткое имя организации
     * @return boolean если регистрация проходит удачно возвращает true
     */
    boolean register(String login, String password, String name) throws MyException;

    /**
     * Вход
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return boolean если пользователь есть в базе вернет true
     */
    boolean login(String login, String password) throws MyException;

    /**
     * Проверика пользователя
     *
     * @param hashCode код который был получен от пользователя
     * @return boolean если коды совпадают вернет true
     */
    boolean activation(String hashCode) throws MyException;
}

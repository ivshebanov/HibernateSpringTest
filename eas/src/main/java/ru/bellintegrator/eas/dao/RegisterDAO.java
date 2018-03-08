package ru.bellintegrator.eas.dao;

public interface RegisterDAO {

    /**
     * Регистрация новой организации
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @param name     краткое имя организации
     * @return boolean если регистрация проходит удачно возвращает true
     */
    boolean register(String login, String password, String name);

    /**
     * Вход
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return boolean если пользователь есть в базе вернет true
     */
    boolean login(String login, String password);

    /**
     * Проверика пользователя
     *
     * @param hashCode код который был получен от пользователя
     * @return boolean если коды совпадают вернет true
     */
    boolean activation(String hashCode);
}
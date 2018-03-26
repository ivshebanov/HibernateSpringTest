package ru.bellintegrator.eas.service;

public interface RegisterService {

    /**
     * Регистрация новой организации
     *
     * @param login    логин организации
     * @param password пароль организации
     * @param name     краткое имя организации
     * @return boolean если регистрация проходит удачно возвращает true
     */
    boolean register(String login, String password, String name);

    /**
     * Вход
     *
     * @param login    логин организации
     * @param password пароль организации
     * @return boolean если организация есть в базе вернет true
     */
    boolean login(String login, String password);

    /**
     * Проверика подлинности организации
     *
     * @param hashCode код который был получен от пользователя
     * @return boolean если коды совпадают вернет true
     */
    boolean activation(String hashCode);
}

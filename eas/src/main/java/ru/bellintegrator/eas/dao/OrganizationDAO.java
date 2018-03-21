package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Organization;

import java.util.List;

public interface OrganizationDAO {

    /**
     * Регистрация новой организации
     *
     * @param login    логин организации
     * @param password пароль организации
     * @param name     краткое имя организации
     * @return boolean если регистрация проходит удачно возвращает true
     */
    boolean register(String login, String password, String name) throws MyException;

    /**
     * Вход
     *
     * @param login    логин организации
     * @param password пароль организации
     * @return boolean если организация есть в базе вернет true
     */
    boolean login(String login, String password) throws MyException;

    /**
     * Проверика подлинности организации
     *
     * @param hashCode код который был получен от пользователя
     * @return boolean если коды совпадают вернет true
     */
    boolean activation(String hashCode) throws MyException;

    /**
     * Получить все объекты Organization
     *
     * @return List<Organization>
     */
    List<Organization> all() throws MyException;

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return Organization
     */
    Organization load(Long id) throws MyException;

    /**
     * Обновиить Organization
     *
     * @param id           идентификатор Organization, которую надо обновить
     * @param organization новый объект Organization
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(Long id, Organization organization) throws MyException;

    /**
     * Удалить Organization по идентификатору
     *
     * @param id идентификатор Organization, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(Long id) throws MyException;

    /**
     * Добавить Organization
     *
     * @param organization
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(Organization organization) throws MyException;
}

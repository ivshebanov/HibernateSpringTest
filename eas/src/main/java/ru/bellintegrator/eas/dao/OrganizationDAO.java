package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Organization;

import java.util.List;

public interface OrganizationDAO {

    /**
     * Получить все объекты Organization
     *
     * @return List<Organization>
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return Organization
     */
    Organization load(long id) throws MyException;

    /**
     * Обновиить Organization
     *
     * @param id           идентификатор Organization, которую надо обновить
     * @param organization новый объект Organization
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(long id, Organization organization) throws MyException;

    /**
     * Удалить Organization по идентификатору
     *
     * @param id идентификатор Organization, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(long id) throws MyException;

    /**
     * Добавить Organization
     *
     * @param organization
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(Organization organization) throws MyException;
}

package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;

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
    Organization load(Long id);

    /**
     * Обновиить Organization
     *
     * @param id           идентификатор Organization, которую надо обновить
     * @param organization новый объект Organization
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(long id, Organization organization);

    /**
     * Удалить Organization по идентификатору
     *
     * @param id идентификатор Organization, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(Long id);

    /**
     * Добавить Organization
     *
     * @param organization
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(Organization organization);
}

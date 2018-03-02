package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Office;

import java.util.List;

public interface OfficeDAO {

    /**
     * Получить все объекты Office из Organization
     *
     * @param orgId идентификатор нужной организации
     * @return List<Office> список офисов
     */
    List<Office> all(int orgId);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return Office
     */
    Office load(Long id);

    /**
     * Обновиить User
     *
     * @param id     идентификатор Office, которую надо удалить
     * @param office новый объект Office
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(long id, Office office);

    /**
     * Удалить Office по идентификатору
     *
     * @param id идентификатор Office, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(Long id);

    /**
     * Добавить Office
     *
     * @param office
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(Office office);
}

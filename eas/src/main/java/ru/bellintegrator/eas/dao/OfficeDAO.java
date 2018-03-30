package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.model.Office;

import java.util.List;

public interface OfficeDAO {

    /**
     * Получить все объекты Office из Organization
     *
     * @param orgId идентификатор нужной организации
     * @return List<Office> список офисов
     */
    List<Office> all(Long orgId);

    /**
     * Получить все объекты Office по заданным параметрам
     *
     * @param orgId    идентификатор нужной организации (обязательняй параметр)
     * @param name
     * @param phone
     * @param isActive
     * @return List<Office> список офисов
     */
    List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive);

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return Office
     */
    Office loadById(Long id);

    /**
     * Обновиить Office
     *
     * @param id     идентификатор Office, которую надо удалить
     * @param office новый объект Office
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(Long id, Office office);

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

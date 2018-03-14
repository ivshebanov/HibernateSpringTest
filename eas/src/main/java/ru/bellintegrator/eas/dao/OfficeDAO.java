package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Office;

import java.util.List;

public interface OfficeDAO {

    /**
     * Получить все объекты Office из Organization
     *
     * @param orgId идентификатор нужной организации
     * @return List<Office> список офисов
     */
    List<Office> all(long orgId) throws MyException;

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return Office
     */
    Office load(long id) throws MyException;

    /**
     * Обновиить User
     *
     * @param id     идентификатор Office, которую надо удалить
     * @param office новый объект Office
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(long id, Office office) throws MyException;

    /**
     * Удалить Office по идентификатору
     *
     * @param id идентификатор Office, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(long id) throws MyException;

    /**
     * Добавить Office
     *
     * @param office
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(Office office) throws MyException;
}

package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Office;

import java.util.List;

public interface OfficeService {

    /**
     * Получить все объекты Office по заданным параметрам
     *
     * @param orgId    идентификатор нужной организации (обязательняй параметр)
     * @param name
     * @param phone
     * @param isActive
     * @return List<Office> список офисов
     */
    List<Office> loadOffice(Long orgId, String name, int phone, boolean isActive) throws MyException;

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return Office
     */
    Office loadById(Long id) throws MyException;

    /**
     * Обновиить Office
     *
     * @param id       идентификатор Office, которую надо удалить
     * @param name
     * @param address
     * @param phone
     * @param isActive
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(Long id, String name, String address, int phone, boolean isActive) throws MyException;

    /**
     * Удалить Office по идентификатору
     *
     * @param id идентификатор Office, которую надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(Long id) throws MyException;

    /**
     * Добавить Office
     *
     * @param name
     * @param address
     * @param phone
     * @param isActive
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(String name, String address, int phone, boolean isActive) throws MyException;
}

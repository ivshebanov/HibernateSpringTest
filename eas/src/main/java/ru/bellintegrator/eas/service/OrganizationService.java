package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Organization;

import java.util.List;

public interface OrganizationService {

    /**
     * Получить Organization по параметрам
     *
     * @param name     имя организации (обязательняй параметр)
     * @param inn      ИНН огранизации
     * @param isActive активирована или нет
     * @return List<Organization>
     */
    List<Organization> loadOrganization(String name, int inn, boolean isActive) throws MyException;

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return Organization
     */
    Organization loadById(Long id) throws MyException;

    /**
     * Обновиить Organization
     *
     * @param id       идентификатор Organization, которую надо обновить
     * @param name
     * @param fullName
     * @param inn
     * @param kpp
     * @param address
     * @param phone
     * @param isActive
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(Long id, String name, String fullName, int inn, int kpp,
                   String address, int phone, boolean isActive) throws MyException;

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
     * @param name
     * @param fullName
     * @param inn
     * @param kpp
     * @param address
     * @param phone
     * @param isActive
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(String name, String fullName, int inn, int kpp,
                 String address, int phone, boolean isActive) throws MyException;
}

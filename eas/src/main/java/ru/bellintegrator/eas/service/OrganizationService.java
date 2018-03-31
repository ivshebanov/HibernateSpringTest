package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.view.OrganizationView;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrganizationService {

    /**
     * Получить Organization по параметрам
     *
     * @param name     имя организации (обязательняй параметр)
     * @param inn
     * @param isActive
     * @return List<Organization>
     */
    List<OrganizationView> loadOrganization(@NotNull String name, int inn, boolean isActive);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return Organization
     */
    OrganizationView loadById(Long id);

    /**
     * Обновиить Organization
     *
     * @param organizationView
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(OrganizationView organizationView);

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
     * @param organizationView
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(OrganizationView organizationView);
}

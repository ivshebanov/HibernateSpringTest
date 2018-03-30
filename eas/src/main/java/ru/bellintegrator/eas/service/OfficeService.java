package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.view.OfficeView;

import javax.validation.constraints.NotNull;
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
    List<Office> loadOffice(@NotNull Long orgId, String name, int phone, boolean isActive);

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
     * @param officeView
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(OfficeView officeView);

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
     * @param officeView
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(OfficeView officeView);
}

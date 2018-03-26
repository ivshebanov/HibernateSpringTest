package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.model.Office;
import ru.bellintegrator.eas.view.OfficeView;

import java.util.List;

public interface OfficeService {

    /**
     * Получить все объекты Office по заданным параметрам
     *
     * @param orgId      идентификатор нужной организации (обязательняй параметр)
     * @param officeView
     * @return List<Office> список офисов
     */
    List<Office> loadOffice(Long orgId, OfficeView officeView);

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

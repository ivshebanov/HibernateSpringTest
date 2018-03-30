package ru.bellintegrator.eas.service;

import ru.bellintegrator.eas.model.User;
import ru.bellintegrator.eas.view.UserView;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    /**
     * Получить все объекты User из офиса Office
     *
     * @param officeId        идентификатор нужного офиса (обязательняй параметр)
     * @param firstName
     * @param secondName
     * @param middleName
     * @param position
     * @param docCode
     * @param citizenshipCode
     * @return List<User> список users
     */
    List<User> loadUser(@NotNull Long officeId, String firstName, String secondName, String middleName,
                        String position, int docCode, int citizenshipCode);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return User
     */
    User loadById(Long id);

    /**
     * Обновиить User
     *
     * @param userView
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(UserView userView);


    /**
     * Удалить User по идентификатору
     *
     * @param id идентификатор пользователя, которого надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(Long id);

    /**
     * Добавить User
     *
     * @param userView
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(UserView userView);
}

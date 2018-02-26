package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.User;

import java.util.List;

public interface UserDAO {

    /**
     * Получить все объекты User из офиса officeId
     *
     * @param officeId идентификатор нужного офиса
     * @return List<User> список users
     */
    List<User> all(int officeId);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return User
     */
    User loadUserById(Long id);

    /**
     * Обновиить User
     *
     * @param id   идентификатор пользователя, которого надо обновить
     * @param user новый объект пользователя
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean updateUserById(long id, User user);

    /**
     * Удалить User по идентификатору
     *
     * @param id идентификатор пользователя, которого надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean deleteUserById(Long id);

    /**
     * Добавить User
     *
     * @param user
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(User user);
}

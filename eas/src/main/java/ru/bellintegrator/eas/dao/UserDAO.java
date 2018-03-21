package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.User;

import java.util.List;

public interface UserDAO {

    /**
     * Получить все объекты User из офиса officeId
     *
     * @param officeId идентификатор нужного офиса
     * @return List<User> список users
     */
    List<User> all(long officeId) throws MyException;

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return User
     */
    User load(long id) throws MyException;

    /**
     * Обновиить User
     *
     * @param id   идентификатор пользователя, которого надо обновить
     * @param user новый объект пользователя
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(long id, User user) throws MyException;

    /**
     * Удалить User по идентификатору
     *
     * @param id идентификатор пользователя, которого надо удалить
     * @return boolean вернет true, если объект удачно удален
     */
    boolean delete(long id) throws MyException;

    /**
     * Добавить User
     *
     * @param user
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(User user) throws MyException;
}

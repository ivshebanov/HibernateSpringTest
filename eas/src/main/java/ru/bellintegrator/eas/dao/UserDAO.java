package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.exception.MyException;
import ru.bellintegrator.eas.model.User;

import java.util.List;

public interface UserDAO {

    /**
     * Получить все объекты User из офиса Office
     *
     * @param officeId идентификатор нужного офиса
     * @return List<User> список users
     */
    List<User> all(Long officeId);

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
    List<User> loadUser(Long officeId, String firstName, String secondName, String middleName,
                        String position, int docCode, int citizenshipCode) throws MyException;

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
     * @param id   идентификатор пользователя, которого надо обновить
     * @param user новый объект пользователя
     * @return boolean вернет true, если объект удачно обновлен
     */
    boolean update(Long id, User user, int docCode, String docName,
                   int citizenshipCode, String citizenshipName) throws MyException;

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
     * @param user
     * @return boolean вернет true, если объект удачно добавлен
     */
    boolean save(User user, int docCode, String docName,
                 int citizenshipCode, String citizenshipName) throws MyException;
}

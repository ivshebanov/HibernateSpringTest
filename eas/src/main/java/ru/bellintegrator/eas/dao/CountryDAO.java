package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Country;

import java.util.List;

public interface CountryDAO {

    /**
     * Получить все объекты Country
     *
     * @return List<Country> список офисов
     */
    List<Country> all() throws MyException;
}

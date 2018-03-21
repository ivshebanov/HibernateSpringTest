package ru.bellintegrator.eas.dao;

import ru.bellintegrator.eas.MyException;
import ru.bellintegrator.eas.model.Doc;

import java.util.List;

public interface DocDAO {

    /**
     * Получить все объекты Doc
     *
     * @return List<Doc> список офисов
     */
    List<Doc> all() throws MyException;
}

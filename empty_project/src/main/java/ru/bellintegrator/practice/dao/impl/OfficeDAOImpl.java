package ru.bellintegrator.practice.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.model.Office;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {

    private final EntityManager em;

    @Autowired
    public OfficeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Office> all(int orgId) {
        return null;
    }

    @Override
    public Office loadOfficeById(Long id) {
        return null;
    }

    @Override
    public boolean updateOfficeById(long id, Office office) {
        return false;
    }

    @Override
    public boolean deleteOfficeById(Long id) {
        return false;
    }

    @Override
    public boolean save(Office office) {
        return false;
    }
}

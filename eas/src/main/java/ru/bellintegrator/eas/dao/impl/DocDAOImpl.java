package ru.bellintegrator.eas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.eas.dao.DocDAO;
import ru.bellintegrator.eas.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocDAOImpl implements DocDAO {

    private final EntityManager em;

    @Autowired
    public DocDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Doc> all()  {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Doc> criteriaQuery = cb.createQuery(Doc.class);
        Root<Doc> docRoot = criteriaQuery.from(Doc.class);
        criteriaQuery.select(docRoot);
        TypedQuery<Doc> query = em.createQuery(criteriaQuery);
        List<Doc> docs = query.getResultList();
        if (docs.isEmpty()) {
            return new ArrayList<>();
        }
        return docs;
    }
}

package ru.puga1chev.crudspring.dao;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class ObjectDaoEntityManagerImpl<T> implements BaseDaoOperations<T> {

    @PersistenceContext
    EntityManager entityManager;
    private final Class<T> model;

    private final static Logger logger = Logger.getLogger(ObjectDaoEntityManagerImpl.class);

    public ObjectDaoEntityManagerImpl(Class<T> model) {
        this.model = model;
    }

    @Override
    @Transactional
    public void insert(T obj) {

        entityManager.persist(obj);
    }

    @Override
    @Transactional
    public void update(T obj) {

        entityManager.merge(obj);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        T findedObj = getById(id);
        entityManager.remove(findedObj);
    }

    @Override
    public T getById(Long id) {

        return entityManager.find(model, id);
    }

    @Override
    public T getByField(String fieldName, String value) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(model);
        Root<T> root = criteria.from(model);
        criteria.select(root).where(builder.equal(root.get(fieldName), value));
        T findedObj = entityManager.createQuery(criteria).getSingleResult();
        return findedObj;
    }

    @Override
    public List<T> getAll(String orderByField) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(model);
        Root<T> rootEntry = criteriaQuery.from(model);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}

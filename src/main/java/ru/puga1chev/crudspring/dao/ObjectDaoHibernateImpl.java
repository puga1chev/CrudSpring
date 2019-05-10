package ru.puga1chev.crudspring.dao;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ObjectDaoHibernateImpl<T> implements BaseDaoOperations<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private final static Logger logger = Logger.getLogger(ObjectDaoHibernateImpl.class);
    private final Class<T> model;

    public ObjectDaoHibernateImpl(Class<T> model) {
        this.model = model;
    }

    @Override
    public void insert(T obj) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при попытке добавления пользователя в БД", e);
        }
    }

    @Override
    public void update(T obj) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(obj);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при обновлении записи в БД", e);
        }
    }

    @Override
    public void delete(Long id) {

        T objDel = getById(id);
        if (objDel == null) {
            logger.error("Пользователь с id " + id + " не найден!");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(objDel);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Ошибка при попытке удалить запись из БД", e);
        }
    }

    @Override
    public T getById(Long id) {
        return getByField("id", id.toString());
    }

    @Override
    public T getByField(String fieldName, String value) {

        T obj = null;
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(model);
            Root<T> root = criteria.from(model);
            criteria.select(root).where(builder.equal(root.get(fieldName), value));
            obj = session.createQuery(criteria).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Ошибка при попытке получить запись из БД", e);
        }
        return obj;
    }

    @Override
    public List<T> getAll(String orderByField) {

        List<T> obj = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(model);
            Root<T> root = criteria.from(model);
            criteria.select(root);
            if (!orderByField.isEmpty()) {
                criteria.orderBy(builder.asc(root.get(orderByField)));
            }
            obj = session.createQuery(criteria).getResultList();
            transaction.commit();
        } catch (Exception e) {
            logger.error("Ошибка при попытке получить все записи из БД", e);
        }
        return obj;

    }
}

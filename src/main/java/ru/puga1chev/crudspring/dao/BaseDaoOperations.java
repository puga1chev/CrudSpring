package ru.puga1chev.crudspring.dao;

import java.util.List;

public interface BaseDaoOperations<T> {

    void insert(T obj);
    void update(T obj);
    void delete(Long id);
    T getById(Long id);
    T getByField(String fieldName, String value);
    List<T> getAll(String orderByField);
}

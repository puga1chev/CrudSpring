package ru.puga1chev.crudspring.service;

import ru.puga1chev.crudspring.entity.Role;

import java.util.List;
import java.util.Map;

public interface ObjectService<T> {

    void insert(T obj);
    void update(T obj);
    void delete(Long id);
    T getById(Long id);
    T getByField(String fieldName, String value);
    List<T> getAll(String orderByField);
    public String toString();
    public Map<String, String> getAllAsMap();

}

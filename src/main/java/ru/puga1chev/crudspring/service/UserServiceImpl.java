package ru.puga1chev.crudspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.puga1chev.crudspring.dao.BaseDaoOperations;
import ru.puga1chev.crudspring.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements ObjectService<User> {

    @Autowired
    private BaseDaoOperations<User> dao;

    @Override
    public List<User> getAll(String orderByField) {
        return dao.getAll(orderByField);
    }

    @Override
    public void insert(User obj) {
        dao.insert(obj);
    }

    @Override
    public void update(User obj) {
        dao.update(obj);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public User getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public User getByField(String fieldName, String value) {
        return dao.getByField(fieldName, value);
    }
}

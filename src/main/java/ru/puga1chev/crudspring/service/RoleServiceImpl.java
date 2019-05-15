package ru.puga1chev.crudspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.puga1chev.crudspring.dao.*;
import ru.puga1chev.crudspring.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements ObjectService<Role> {

    @Autowired
    private BaseDaoOperations<Role> dao;

    @Override
    public List<Role> getAll(String orderByField) {
        return dao.getAll(orderByField);
    }

    @Override
    public void insert(Role obj) {
        dao.insert(obj);
    }

    @Override
    public void update(Role obj) {
        dao.update(obj);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Role getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public Role getByField(String fieldName, String value) {
        return dao.getByField(fieldName, value);
    }

/*    public Role getRole(String rolename) {
        Role role = getByField("rolename", rolename);

        if (role == null) {
            role = new Role(0L, rolename);
            insert(role);
        }
        return role;
    }*/

    public Map<String, String> getAllAsMap() {
        Map<String, String> roles = new HashMap<>();
        getAll("id").forEach(x -> roles.put(x.getId().toString(), x.getRolename()));
        return roles;
    }
}

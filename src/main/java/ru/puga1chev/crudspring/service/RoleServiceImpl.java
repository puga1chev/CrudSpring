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

    public static Role getRole(ObjectService<Role> roleService, String rolename) {
        Role role = roleService.getByField("rolename", rolename);

        if (role == null) {
            role = new Role(0L, rolename);
            roleService.insert(role);
        }
        return role;
    }

    public static Map<String, String> getRolesAsMap(ObjectService<Role> roleService) {
        Map<String, String> roles = new HashMap<>();
        roleService.getAll("id").stream().forEach(x -> roles.put(x.getId().toString(), x.getRolename()));
        return roles;
    }
}

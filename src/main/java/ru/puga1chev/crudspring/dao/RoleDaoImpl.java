package ru.puga1chev.crudspring.dao;

import org.springframework.stereotype.Repository;
import ru.puga1chev.crudspring.entity.Role;

@Repository
public class RoleDaoImpl extends ObjectDaoEntityManagerImpl<Role> {
    public RoleDaoImpl() {
        super(Role.class);
    }
}


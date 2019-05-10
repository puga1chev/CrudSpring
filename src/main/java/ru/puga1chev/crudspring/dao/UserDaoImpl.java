package ru.puga1chev.crudspring.dao;

import org.springframework.stereotype.Repository;
import ru.puga1chev.crudspring.entity.User;

@Repository
public class UserDaoImpl extends ObjectDaoEntityManagerImpl<User> {
    public UserDaoImpl() {
        super(User.class);
    }
}

package ru.puga1chev.crudspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.puga1chev.crudspring.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ObjectService<User> userService;

    //@Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userService.getByField("login", login);

        UserBuilder builder = null;
        if (user != null) {

            builder = org.springframework.security.core.userdetails.User.withUsername(login);
            builder.disabled(!user.isEnabled());
            builder.password(user.getPassword());
            String[] authorities = user.getAuthorities()
                    .stream().map(a -> a.getAuthority()).toArray(String[]::new);

            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("Пользователь не найден в базе.");
        }
        return builder.build();

    }

}
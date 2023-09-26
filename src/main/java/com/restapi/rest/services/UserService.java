package com.restapi.rest.services;

import com.restapi.rest.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    User findByUserId(long id);

    List<User> getAllUsers();

    User updateUser(User updateUser);

    void deleteUser(long id);

    User findByEmail(String email);

}

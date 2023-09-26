package com.restapi.rest.configuration;

import com.restapi.rest.entities.Role;
import com.restapi.rest.entities.User;
import com.restapi.rest.services.RoleService;
import com.restapi.rest.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserData {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {
        Role adminRole = new Role("ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        roleService.saveRole(adminRole);

        Role userRole = new Role("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        roleService.saveRole(userRole);

        User admin = new User("admin", "admin", (byte) 20, "admin@mail.ru", "admin", adminRoles);

        userService.saveUser(admin);

        User user = new User("user", "user", (byte) 20, "user@mail.ru", "user", userRoles);

        userService.saveUser(user);
    }
}

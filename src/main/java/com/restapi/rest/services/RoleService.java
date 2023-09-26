package com.restapi.rest.services;

import com.restapi.rest.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void saveRole(Role role);

    Role getRoleById(long id);
}

package com.tutorial.spring.domain.role.service;

import com.tutorial.spring.domain.role.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role createRole(Role role);

    Optional<Role> readRole(Integer id);

    List<Role> readAllRoles();

    void deleteRole(Integer id);
}

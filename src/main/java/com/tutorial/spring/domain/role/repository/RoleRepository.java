package com.tutorial.spring.domain.role.repository;

import com.tutorial.spring.domain.role.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> getByName(String name);
}
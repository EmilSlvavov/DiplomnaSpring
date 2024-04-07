package com.tutorial.spring.domain.role.service;

import com.tutorial.spring.domain.role.Role;
import com.tutorial.spring.domain.role.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    public final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> readRole(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> readAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}

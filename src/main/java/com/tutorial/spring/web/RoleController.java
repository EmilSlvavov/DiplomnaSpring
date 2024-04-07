package com.tutorial.spring.web;

import com.tutorial.spring.domain.role.Role;
import com.tutorial.spring.domain.role.service.RoleService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    ResponseEntity<List<Role>> rcGetByAll() {
        return ResponseEntity.ok(roleService.readAllRoles());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity<Optional<Role>> rcGetById(@PathVariable Integer id) {
        return ResponseEntity.ok(roleService.readRole(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    ResponseEntity<Role> rcPostBody(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> rcDeleteBody(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
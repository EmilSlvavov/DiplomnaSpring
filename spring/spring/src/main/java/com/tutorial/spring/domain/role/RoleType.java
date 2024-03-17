package com.tutorial.spring.domain.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String name;

    public static final boolean isRole(Role source, RoleType target) {
        return source.getName().equals(target.name);
    }

}

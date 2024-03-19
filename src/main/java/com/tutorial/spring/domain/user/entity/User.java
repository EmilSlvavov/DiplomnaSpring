package com.tutorial.spring.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.role.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    private Boolean locked;
    @JsonIgnore
    private Boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Property> properties;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Role role;
}

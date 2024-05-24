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
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @JsonIgnore
    private Boolean locked;
    @JsonIgnore
    private Boolean enabled;

    private String imageName;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Property> properties;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Role role;
}

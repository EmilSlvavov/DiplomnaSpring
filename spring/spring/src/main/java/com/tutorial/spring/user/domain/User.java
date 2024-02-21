package com.tutorial.spring.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tutorial.spring.property.domain.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Property> properties;
}

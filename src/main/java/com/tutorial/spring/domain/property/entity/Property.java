package com.tutorial.spring.domain.property.entity;

import com.tutorial.spring.domain.property.enums.BuildType;
import com.tutorial.spring.domain.property.enums.City;
import com.tutorial.spring.domain.property.enums.PropertyType;
import com.tutorial.spring.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @NotNull
    private PropertyType propertyType;
    @NotNull
    private String address;
    @NotNull
    private BuildType buildType;
    @NotNull
    private City city;
    @NotNull
    private BigDecimal pricePerSqMeter;
    @NotNull
    private Double sqMeters;
    @NotNull
    private Integer floor;
    private Integer totalBuildingFloors;
    @NotNull
    private Boolean act16;
    private Boolean elevator;
    private Boolean furnished;


    @ManyToOne
    private User user;
}

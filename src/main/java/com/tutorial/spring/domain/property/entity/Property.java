package com.tutorial.spring.domain.property.entity;

import com.tutorial.spring.domain.property.enums.BuildType;
import com.tutorial.spring.domain.property.enums.PropertyType;
import com.tutorial.spring.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Integer id;
    private PropertyType propertyType;
    private String address;
    private BuildType buildType;
    private BigDecimal pricePerSqMeter;
    private Double sqMeters;
    private Integer floor;
    private Integer totalBuildingFloors;
    private Boolean act16;
    private Boolean elevator;
    private Boolean furnished;


    @ManyToOne
    private User user;
}

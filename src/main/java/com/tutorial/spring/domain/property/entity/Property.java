package com.tutorial.spring.domain.property.entity;

import com.tutorial.spring.domain.property.enums.BuildType;
import com.tutorial.spring.domain.property.enums.City;
import com.tutorial.spring.domain.property.enums.ListingType;
import com.tutorial.spring.domain.property.enums.PropertyType;
import com.tutorial.spring.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Integer id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @NotNull
    private String address;
    @NotNull
    private Integer rooms;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BuildType buildType;
    @NotNull
    @Enumerated(EnumType.STRING)
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
    @Enumerated(EnumType.STRING)
    private ListingType listingType;
    private String imageName;


    @ManyToOne
    private User user;
}

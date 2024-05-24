package com.tutorial.spring.domain.property.propertyDto;

import com.tutorial.spring.domain.property.enums.BuildType;
import com.tutorial.spring.domain.property.enums.City;
import com.tutorial.spring.domain.property.enums.ListingType;
import com.tutorial.spring.domain.property.enums.PropertyType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    private PropertyType propertyType;
    private String address;
    private Integer rooms;
    private BuildType buildType;
    private City city;
    private BigDecimal pricePerSqMeter;
    private Double sqMeters;
    private Integer floor;
    private Integer totalBuildingFloors;
    private Boolean act16;
    private Boolean elevator;
    private Boolean furnished;
    private Integer userId;
    private ListingType listingType;
    private String imageName;
}

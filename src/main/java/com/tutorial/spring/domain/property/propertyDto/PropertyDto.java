package com.tutorial.spring.domain.property.propertyDto;

import com.tutorial.spring.domain.property.enums.BuildType;
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
    private Integer userId;
}

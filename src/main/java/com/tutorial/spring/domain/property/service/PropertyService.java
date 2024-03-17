package com.tutorial.spring.domain.property.service;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import java.util.List;
import java.util.Optional;

public interface PropertyService {

    Property createProperty(PropertyDto propertyDto, Integer id);

    Optional<Property> readProperty(Integer id);

    List<Property> readAllProperties();

    Property updateProperty(Integer id, PropertyDto propertyDto);

    void deleteProperty(Integer id);

}

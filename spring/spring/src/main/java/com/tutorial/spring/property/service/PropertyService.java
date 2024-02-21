package com.tutorial.spring.property.service;

import com.tutorial.spring.property.domain.Property;
import com.tutorial.spring.property.propertyDto.PropertyDto;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
        Property createProperty(PropertyDto propertyDto) ;

        Optional<Property> readProperty(Integer id);

        List<Property> readAllProperties();

        Property updateProperty(Integer id, PropertyDto propertyDto);
        void deleteProperty(Integer id);
}

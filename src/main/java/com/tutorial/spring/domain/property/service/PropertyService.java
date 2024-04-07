package com.tutorial.spring.domain.property.service;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PropertyService {

    Property createProperty(PropertyDto propertyDto, Integer id);

    Optional<Property> readProperty(Integer id);

    Page<Property> readAllProperties(Specification<Property> specification, Pageable pageable);

    Property updateProperty(Integer id, PropertyDto propertyDto);

    void deleteProperty(Integer id);

}

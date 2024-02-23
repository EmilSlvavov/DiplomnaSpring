package com.tutorial.spring.property.mappers;

import com.tutorial.spring.property.domain.Property;
import com.tutorial.spring.property.propertyDto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDto propertyToPropertyDto (Property property);
    @Mapping(target = "id", source = "id")
    Property propertyDtoToProperty (PropertyDto propertyDto, Integer id);
}

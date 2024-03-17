package com.tutorial.spring.infrastucture.mappers;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    PropertyDto propertyToPropertyDto(Property property);

    @Mapping(target = "id", source = "id")
    Property propertyDtoToProperty(PropertyDto propertyDto, Integer id);
}

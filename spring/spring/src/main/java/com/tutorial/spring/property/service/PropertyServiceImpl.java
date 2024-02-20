package com.tutorial.spring.property.service;

import com.tutorial.spring.property.domain.Property;
import com.tutorial.spring.property.mappers.PropertyMapper;
import com.tutorial.spring.property.propertyDto.PropertyDto;
import com.tutorial.spring.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Property createProperty(PropertyDto propertyDto) {
        return propertyRepository.save(propertyMapper.propertyDtoToProperty(propertyDto, null));
    }

    @Override
    public Optional<Property> readProperty(Integer id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> readAllUsers() {
        List<Property> properties = new ArrayList<>();
        propertyRepository.findAll().forEach(properties::add);
        return properties;
    }

    @Override
    public Property updateProperty(Integer id, PropertyDto propertyDto) {
        return propertyRepository.save(propertyMapper.propertyDtoToProperty(propertyDto, id));
    }

    @Override
    public void deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
    }
}

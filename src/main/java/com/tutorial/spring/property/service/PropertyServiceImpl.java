package com.tutorial.spring.property.service;

import com.tutorial.spring.property.domain.Property;
import com.tutorial.spring.property.mappers.PropertyMapper;
import com.tutorial.spring.property.propertyDto.PropertyDto;
import com.tutorial.spring.property.repository.PropertyRepository;
import com.tutorial.spring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Property createProperty(PropertyDto propertyDto) {
        userRepository.findById(propertyDto.getUser_id());
        Property property = propertyMapper.propertyDtoToProperty(propertyDto, null);
        property.setUser(userRepository.findById(propertyDto.getUser_id()).get());
        return propertyRepository.save(property);
    }

    @Override
    public Optional<Property> readProperty(Integer id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> readAllProperties() {
        return propertyRepository.findAll();
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

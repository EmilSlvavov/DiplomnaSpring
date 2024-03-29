package com.tutorial.spring.domain.property.service;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.property.repository.PropertyRepository;
import com.tutorial.spring.domain.user.repository.UserRepository;
import com.tutorial.spring.infrastucture.mappers.PropertyMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Property createProperty(PropertyDto propertyDto, Integer id) {
        userRepository.findById(propertyDto.getUserId());
        Property property = propertyMapper.propertyDtoToProperty(propertyDto, null);
        property.setUser(userRepository.findById(id).get());
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

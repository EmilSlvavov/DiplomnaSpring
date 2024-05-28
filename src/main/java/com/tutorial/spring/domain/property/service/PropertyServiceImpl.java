package com.tutorial.spring.domain.property.service;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.property.repository.PropertyRepository;
import com.tutorial.spring.domain.user.repository.UserRepository;
import com.tutorial.spring.infrastucture.mappers.PropertyMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Property createProperty(PropertyDto propertyDto, Integer id) {
        Property property = propertyMapper.propertyDtoToProperty(propertyDto, null);
        property.setTotalPrice(property.getPricePerSqMeter().multiply(BigDecimal.valueOf(property.getSqMeters())));
        property.setUser(userRepository.findById(id).get());
        return propertyRepository.save(property);
    }

    @Override
    public Optional<Property> readProperty(Integer id) {
        return propertyRepository.findById(id);
    }

    @Override
    public Page<Property> readAllProperties(Specification<Property> specification,
        Pageable pageable) {
        return propertyRepository.findAll(specification, pageable);
    }

    @Override
    public Property updateProperty(Integer id, PropertyDto propertyDto) {
        Property property = propertyMapper.propertyDtoToProperty(propertyDto, id);
        property.setTotalPrice(property.getPricePerSqMeter().multiply(BigDecimal.valueOf(property.getSqMeters())));
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
    }

}

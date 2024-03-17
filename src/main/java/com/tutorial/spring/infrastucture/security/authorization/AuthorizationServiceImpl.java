package com.tutorial.spring.infrastucture.security.authorization;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final PropertyService propertyService;

    @Override
    public boolean isAccessingSelf(Integer id, Integer authenticationId) {
        return id == authenticationId;
    }

    @Override
    public boolean IsAccessingOwnProperty(Integer propertyId, Integer userId) {
        Property property = propertyService.readProperty(propertyId).get();
        return property.getUser().getId() == userId;
    }


}

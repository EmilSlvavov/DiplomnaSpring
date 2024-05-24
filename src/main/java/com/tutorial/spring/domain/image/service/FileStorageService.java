package com.tutorial.spring.domain.image.service;

import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.user.dto.UserDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    UserDto createUserImage (Integer id, MultipartFile multipartFile);

    UserDto deleteUserImage (Integer id);

    PropertyDto createPropertyImage (Integer id, MultipartFile multipartFile);

    PropertyDto deletePropertyImage (Integer id);

    byte[] loadImage (String fileName);


}

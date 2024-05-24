package com.tutorial.spring.domain.image.service;

import com.tutorial.spring.domain.image.repository.FilesStorageRepository;
import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.property.repository.PropertyRepository;
import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.entity.User;
import com.tutorial.spring.domain.user.repository.UserRepository;
import com.tutorial.spring.infrastucture.mappers.PropertyMapper;
import com.tutorial.spring.infrastucture.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService{
    private final FilesStorageRepository filesStorageRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;


    @Override
    public UserDto createUserImage(Integer id, MultipartFile multipartFile) {
        User byId = userRepository.findById(id).get();
        String url = filesStorageRepository.save(multipartFile);
        byId.setImageName(url);
        userRepository.save(byId);
        return (userMapper.userToUserDto(byId));
    }

    @Override
    public UserDto deleteUserImage(Integer id) {
        User byId = userRepository.findById(id).get();
        String path = "";
        filesStorageRepository.delete(byId.getImageName());
        byId.setImageName(path);
        userRepository.save(byId);
        return (userMapper.userToUserDto(byId));
    }

    @Override
    public PropertyDto createPropertyImage(Integer id, MultipartFile multipartFile) {
        Property byId = propertyRepository.findById(id).get();
        String url = filesStorageRepository.save(multipartFile);
        byId.setImageName(url);
        propertyRepository.save(byId);
        return (propertyMapper.propertyToPropertyDto(byId));
    }

    @Override
    public PropertyDto deletePropertyImage(Integer id) {
        Property byId = propertyRepository.findById(id).get();
        String path = "";
        filesStorageRepository.delete((byId.getImageName()));
        byId.setImageName(path);
        propertyRepository.save(byId);
        return (propertyMapper.propertyToPropertyDto(byId));
    }

    @Override
    public byte[] loadImage(String fileName) {
        return (filesStorageRepository.load(fileName));
    }
}

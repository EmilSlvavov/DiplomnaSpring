package com.tutorial.spring.infrastucture.mappers;

import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.dto.UserMeDto;
import com.tutorial.spring.domain.user.dto.UserRegisterDto;
import com.tutorial.spring.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mapping(target = "id", source = "id")
    User userDtoToUser(UserDto user, Integer id);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    User userRegisterDtoToUser(UserRegisterDto user, Integer id);

    void updateUser(@MappingTarget User user, UserDto userDto);

    UserMeDto userToUserMeDto(User user);
}

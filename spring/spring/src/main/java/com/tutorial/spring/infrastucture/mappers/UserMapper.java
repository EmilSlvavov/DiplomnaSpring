package com.tutorial.spring.infrastucture.mappers;

import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mapping(target = "id", source = "id")
    User userDtoToUser(UserDto user, Integer id);

}

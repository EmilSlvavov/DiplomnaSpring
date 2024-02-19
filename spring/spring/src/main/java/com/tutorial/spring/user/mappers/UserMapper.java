package com.tutorial.spring.user.mappers;

import com.tutorial.spring.user.domain.User;
import com.tutorial.spring.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto (User user);
    @Mapping(target = "id", source = "id")
    User userDtoToUser (UserDto user, Integer id);

}

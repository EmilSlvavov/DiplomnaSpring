package com.tutorial.spring.user.mappers;

import com.tutorial.spring.user.domain.User;
import com.tutorial.spring.user.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T23:14:37+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setName( user.getName() );
        userDto.setEmail( user.getEmail() );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto user, Integer id) {
        if ( user == null && id == null ) {
            return null;
        }

        User user1 = new User();

        if ( user != null ) {
            user1.setName( user.getName() );
            user1.setEmail( user.getEmail() );
        }
        user1.setId( id );

        return user1;
    }
}

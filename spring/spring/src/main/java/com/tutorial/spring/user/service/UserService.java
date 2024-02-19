package com.tutorial.spring.user.service;

import com.tutorial.spring.user.domain.User;
import com.tutorial.spring.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto userDto) ;

    Optional<User> readUser(Integer id);

    List<User> readAllUsers();

    User updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);
}

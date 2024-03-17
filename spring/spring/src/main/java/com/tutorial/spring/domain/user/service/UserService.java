package com.tutorial.spring.domain.user.service;


import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserDto userDto);

    Optional<User> readUser(Integer id);

    List<User> readAllUsers();

    User updateUser(Integer id, UserDto userDto);

    void deleteUser(Integer id);

    User getByEmail(String email);
}

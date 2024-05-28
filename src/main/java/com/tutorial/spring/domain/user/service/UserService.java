package com.tutorial.spring.domain.user.service;


import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.dto.UserRegisterDto;
import com.tutorial.spring.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(UserDto userDto);

    User userRegister(UserRegisterDto userRegisterDto);

    Optional<User> readUser(Integer id);

    Page<User> readAllUsers(String name, Pageable pageable);

    User updateUser(Integer id, UserDto userDto);

    void deleteUser(Integer id);

    User getByEmail(String email);
}

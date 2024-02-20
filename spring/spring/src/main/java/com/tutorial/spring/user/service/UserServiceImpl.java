package com.tutorial.spring.user.service;

import com.tutorial.spring.user.domain.User;
import com.tutorial.spring.user.dto.UserDto;
import com.tutorial.spring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tutorial.spring.user.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserDto userDto) {
        return userRepository.save(userMapper.userDtoToUser(userDto, null));
    }

    @Override
    public Optional<User> readUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User updateUser(Integer id, UserDto userDto) {
        return userRepository.save(userMapper.userDtoToUser(userDto, id));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}


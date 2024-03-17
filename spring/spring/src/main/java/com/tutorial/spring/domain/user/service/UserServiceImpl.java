package com.tutorial.spring.domain.user.service;

import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.entity.User;
import com.tutorial.spring.domain.user.repository.UserRepository;
import com.tutorial.spring.infrastucture.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}


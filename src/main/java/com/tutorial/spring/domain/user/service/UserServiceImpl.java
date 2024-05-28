package com.tutorial.spring.domain.user.service;

import com.tutorial.spring.domain.role.Role;
import com.tutorial.spring.domain.role.RoleType;
import com.tutorial.spring.domain.role.repository.RoleRepository;
import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.dto.UserRegisterDto;
import com.tutorial.spring.domain.user.entity.User;
import com.tutorial.spring.domain.user.repository.UserRepository;
import com.tutorial.spring.infrastucture.mappers.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@CrossOrigin
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(UserDto userDto) {
        return userRepository.save(userMapper.userDtoToUser(userDto, null));
    }

    @Override
    public User userRegister(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        User user = userMapper.userRegisterDtoToUser(userRegisterDto, null);
        Role role = roleRepository.getByName(RoleType.ROLE_USER.getName()).get();
        user.setRole(role);
        return userRepository.save(user);
    }


    @Override
    public Optional<User> readUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> readAllUsers(String name, Pageable pageable) {
        if (name== null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findByNameContaining(name, pageable);
    }

    @Override
    public User updateUser(Integer id, UserDto userDto) {
        User byId = userRepository.findById(id).get();
        userMapper.updateUser(byId, userDto);
        return userRepository.save(byId);
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


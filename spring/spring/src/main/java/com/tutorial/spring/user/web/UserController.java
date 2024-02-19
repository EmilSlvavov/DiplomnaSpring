package com.tutorial.spring.user.web;

import com.tutorial.spring.user.domain.User;
import com.tutorial.spring.user.dto.UserDto;
import com.tutorial.spring.user.service.UserService;
import com.tutorial.spring.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> rcGetById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.readUser(id));
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> rcGetByAll() {
        return ResponseEntity.ok(userService.readAllUsers());
    }

    @PutMapping("/{id}")
    ResponseEntity<User> rcRequestBody(@PathVariable Integer id, @Valid @RequestBody UserDto user) {

        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PostMapping
    ResponseEntity<User> rcPostBody(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> reDeleteBody(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

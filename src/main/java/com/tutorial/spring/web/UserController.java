package com.tutorial.spring.web;


import com.tutorial.spring.domain.user.dto.UserDto;
import com.tutorial.spring.domain.user.dto.UserMeDto;
import com.tutorial.spring.domain.user.dto.UserRegisterDto;
import com.tutorial.spring.domain.user.entity.User;
import com.tutorial.spring.domain.user.service.UserService;
import com.tutorial.spring.infrastucture.mappers.UserMapper;
import com.tutorial.spring.infrastucture.security.authentication.CustomUserDetails;
import com.tutorial.spring.infrastucture.security.authorization.AuthorizationService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    ResponseEntity<User> rcGetById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.readUser(id).get());
    }

    @GetMapping("/me")
    ResponseEntity<UserMeDto> getCurrentUser() {
        CustomUserDetails principal1 = (CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        return ResponseEntity.ok(userMapper.userToUserMeDto(userService.readUser(principal1.getUser().getId()).get()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    ResponseEntity<List<User>> rcGetByAll() {
        return ResponseEntity.ok(userService.readAllUsers());
    }

    @PreAuthorize("hasAuthority('ADMIN') || @authorizationService.isAccessingSelf(#id, authentication.principal.user.id)")
    @PutMapping("/{id}")
    ResponseEntity<User> rcRequestBody(@PathVariable Integer id, @Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @PostMapping
    ResponseEntity<User> rcPostBody(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return new ResponseEntity<>(userService.userRegister(userRegisterDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') || @authorizationService.isAccessingSelf(#id, authentication.principal.user.id)")
    @DeleteMapping("/{id}")
    ResponseEntity<?> rcDeleteBody(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

package com.tutorial.spring.infrastucture.security.authentication;

import com.tutorial.spring.domain.user.entity.User;
import com.tutorial.spring.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                String.format("User with the specified username: %s is not found", email));
        }
        return new CustomUserDetails(user);
    }
}

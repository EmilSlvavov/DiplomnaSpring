package com.tutorial.spring.web;

import com.tutorial.spring.infrastucture.security.authentication.AuthenticationRequest;
import com.tutorial.spring.infrastucture.security.authentication.AuthenticationResponse;
import com.tutorial.spring.infrastucture.security.authentication.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
        @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect username or password", exception);
        }

        return ResponseEntity.ok(new AuthenticationResponse(jwtUtil.createToken(authentication)));
    }
}

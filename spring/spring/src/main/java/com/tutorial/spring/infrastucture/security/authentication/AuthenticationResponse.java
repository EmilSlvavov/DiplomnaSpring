package com.tutorial.spring.infrastucture.security.authentication;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponse implements Serializable {

    private final String jwt;
}

package com.tutorial.spring.infrastucture.security.authentication;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest implements Serializable {

    private String username;

    private String password;
}

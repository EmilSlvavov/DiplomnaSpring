package com.tutorial.spring.infrastucture.security.authorization;

public interface AuthorizationService {

    boolean isAccessingSelf(Integer id, Integer authenticationId);

    boolean IsAccessingOwnProperty(Integer userId, Integer id);
}

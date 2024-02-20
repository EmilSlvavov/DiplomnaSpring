package com.tutorial.spring.user.repository;

import com.tutorial.spring.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}


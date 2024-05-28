package com.tutorial.spring.domain.user.repository;

import com.tutorial.spring.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>{

    Page<User> findByNameContaining(String name, Pageable pageable);

    boolean existsByEmail(String email);

    User findByEmail(String email);
}


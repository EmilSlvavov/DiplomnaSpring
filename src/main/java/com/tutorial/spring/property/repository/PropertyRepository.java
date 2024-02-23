package com.tutorial.spring.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tutorial.spring.property.domain.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}

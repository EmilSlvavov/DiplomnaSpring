package com.tutorial.spring.domain.property.repository;

import com.tutorial.spring.domain.property.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

}

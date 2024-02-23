package com.tutorial.spring.property.web;

import com.tutorial.spring.property.domain.Property;
import com.tutorial.spring.property.propertyDto.PropertyDto;
import com.tutorial.spring.property.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping("/{id}")
    ResponseEntity<Optional<Property>> rcGetById(@PathVariable Integer id) {
        return ResponseEntity.ok(propertyService.readProperty(id));
    }

    @GetMapping("/all")
    ResponseEntity<List<Property>> rcGetByAll() {
        return ResponseEntity.ok(propertyService.readAllProperties());
    }

    @PutMapping("/{id}")
    ResponseEntity<Property> rcRequestBody(@PathVariable Integer id, @Valid @RequestBody PropertyDto propertyDto) {

        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDto));
    }

    @PostMapping
    ResponseEntity<Property> rcPostBody(@Valid @RequestBody PropertyDto propertyDto) {
        return new ResponseEntity<>(propertyService.createProperty(propertyDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> rcDeleteBody(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}

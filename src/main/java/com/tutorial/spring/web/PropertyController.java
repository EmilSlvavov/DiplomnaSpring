package com.tutorial.spring.web;

import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.property.service.PropertyService;
import com.tutorial.spring.infrastucture.security.authentication.CustomUserDetails;
import com.tutorial.spring.infrastucture.security.authorization.AuthorizationService;
import com.tutorial.spring.infrastucture.security.filter.PropertySpecificationsBuilder;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    ResponseEntity<Optional<Property>> rcGetById(@PathVariable Integer id) {
        return ResponseEntity.ok(propertyService.readProperty(id));
    }

    @GetMapping("/all")
    ResponseEntity<Page<Property>> rcGetByAll(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        PropertySpecificationsBuilder builder = new PropertySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), null, null);
        }

        Specification<Property> spec = builder.build();

        return ResponseEntity.ok(propertyService.readAllProperties(spec, pageable));
    }

    @PreAuthorize("hasAuthority('ADMIN') || @authorizationService.IsAccessingOwnProperty(#id, authentication.principal.user.id)")
    @PutMapping("/{id}")
    ResponseEntity<Property> rcRequestBody(@PathVariable Integer id,
        @Valid @RequestBody PropertyDto propertyDto) {

        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDto));
    }


    @PostMapping
    ResponseEntity<Property> rcPostBody(@Valid @RequestBody PropertyDto propertyDto) {
        CustomUserDetails principal1 = (CustomUserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
        return new ResponseEntity<>(
            propertyService.createProperty(propertyDto, principal1.getUser().getId()),
            HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN') || @authorizationService.IsAccessingOwnProperty(#id, authentication.principal.user.id)")
    @DeleteMapping("/{id}")
    ResponseEntity<?> rcDeleteBody(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}

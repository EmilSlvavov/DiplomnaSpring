package com.tutorial.spring.web;

import com.tutorial.spring.domain.image.service.FileStorageService;
import com.tutorial.spring.domain.property.entity.Property;
import com.tutorial.spring.domain.property.propertyDto.PropertyDto;
import com.tutorial.spring.domain.user.dto.UserDto;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController {
    private final FileStorageService fileStorageService;

    @PostMapping("/users/{id}")
    public ResponseEntity<UserDto> uploadUserImage(@PathVariable Integer id,@RequestParam("file") MultipartFile multipartFile) {
        try {
            return ResponseEntity.ok(fileStorageService.createUserImage(id, multipartFile));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserImage(@PathVariable Integer id) {
        try {
            fileStorageService.deleteUserImage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/properties/{id}")
    public ResponseEntity<PropertyDto> uploadPropertyImage(@PathVariable Integer id,@RequestParam("file") MultipartFile multipartFile) {
        try {
            return ResponseEntity.ok(fileStorageService.createPropertyImage(id, multipartFile));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<?> deletePropertyImage(@PathVariable Integer id) {
        try {
            fileStorageService.deletePropertyImage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> loadImage(@PathVariable String filename) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(fileStorageService.loadImage(filename));
    }
}

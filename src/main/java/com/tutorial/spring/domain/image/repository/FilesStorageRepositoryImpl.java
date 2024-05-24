package com.tutorial.spring.domain.image.repository;

import java.nio.file.FileAlreadyExistsException;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FilesStorageRepositoryImpl implements FilesStorageRepository{

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            return file.getOriginalFilename();
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] load(String filename) {
        try {
            Path file = root.resolve(filename);

            return Files.readAllBytes(file);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(String filename) {
        try{
            Path file = root.resolve((filename));
            return Files.deleteIfExists((file));
        } catch (IOException e) {
            return false;
        }
    }
}

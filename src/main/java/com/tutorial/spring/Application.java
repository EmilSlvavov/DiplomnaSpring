package com.tutorial.spring;

import com.tutorial.spring.domain.image.repository.FilesStorageRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Resource
    FilesStorageRepository filesStorageRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        filesStorageRepository.init();
    }
}


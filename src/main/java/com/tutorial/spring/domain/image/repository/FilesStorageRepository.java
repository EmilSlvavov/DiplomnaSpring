package com.tutorial.spring.domain.image.repository;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageRepository {
    void init();

    String save(MultipartFile file);

    byte[] load(String filename);

    boolean delete(String filename);

}

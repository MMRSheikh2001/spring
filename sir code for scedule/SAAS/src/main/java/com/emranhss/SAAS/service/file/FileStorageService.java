package com.emranhss.SAAS.service.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${image.upload.dir}")
    private String uploadDir;

//    public String save(MultipartFile file) throws IOException {
//
//        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
//        String fileName = UUID.randomUUID() + "_" + originalName;
//        Path path = Paths.get(uploadDir, fileName);
//
//        Files.createDirectories(path.getParent());
//        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//        return "/images/" + fileName; // URL stored in DB
//    }


    public String save(MultipartFile file) throws IOException {

        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = UUID.randomUUID() + "_" + originalName;

        // Ensure directory exists
        Path targetLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(targetLocation);

        Path filePath = targetLocation.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // In dev, return absolute path or relative to upload dir
        return fileName;  // store just the file name in DB
    }


}

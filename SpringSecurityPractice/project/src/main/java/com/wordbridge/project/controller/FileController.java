package com.wordbridge.project.controller;

import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @GetMapping("/userprofiles/{filename}")
    public ResponseEntity<Resource> getUserProfileImage(
            @PathVariable String filename) throws IOException {

        Path path = Paths.get(uploadDir, "userprofiles", filename);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @GetMapping("/companyprofiles/{filename}")
    public ResponseEntity<Resource> getCompanyProfileImage(
            @PathVariable String filename) throws IOException {

        Path path = Paths.get(uploadDir, "companyprofiles", filename);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}

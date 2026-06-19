package com.wordbridge.project.controller;

import com.wordbridge.project.dto.responsedto.ResumeResponseDTO;
import com.wordbridge.project.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume/")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("{userProfileId}")
    public ResponseEntity<ResumeResponseDTO>
    generateResume(
            @PathVariable Long userProfileId
    ) {

        return ResponseEntity.ok(
                resumeService.generateResume(
                        userProfileId
                )
        );
    }


}

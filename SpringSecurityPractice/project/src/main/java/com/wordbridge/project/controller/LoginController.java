package com.wordbridge.project.controller;

import com.wordbridge.project.dto.requestdto.LoginRequestDTO;
import com.wordbridge.project.dto.responsedto.LoginResponseDTO;
import com.wordbridge.project.serviceimpl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

}

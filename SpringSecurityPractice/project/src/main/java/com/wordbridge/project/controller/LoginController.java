package com.wordbridge.project.controller;

import com.wordbridge.project.dto.requestdto.ForgotPasswordRequestDTO;
import com.wordbridge.project.dto.requestdto.LoginRequestDTO;
import com.wordbridge.project.dto.requestdto.ResetPasswordRequestDTO;
import com.wordbridge.project.dto.responsedto.LoginResponseDTO;
import com.wordbridge.project.serviceimpl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("verifyemail")
    public ResponseEntity<String> verifyEmail(@RequestParam String token){
        authService.verifyEmail(token);
     return    ResponseEntity.ok("Email Verified Successfully.You can log in");
    }


    // Forgot Password
    @PostMapping("forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDTO dto) {
        authService.forgotPassword(dto);
        return ResponseEntity.ok("Password reset link sent to " + dto.getEmail());
    }

    // Reset Password
    @PostMapping("reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO dto) {
        authService.resetPasswordUsingToken(dto);
        return ResponseEntity.ok("Password reset successful. You can now log in with your new password.");
    }

}

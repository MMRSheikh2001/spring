package com.emranhss.SAAS.controller;

import com.emranhss.SAAS.dto.request.LoginRequest;
import com.emranhss.SAAS.dto.request.RegisterRequest;
import com.emranhss.SAAS.dto.response.AuthResponse;
import com.emranhss.SAAS.entity.User;
import com.emranhss.SAAS.repository.TokenRepository;
import com.emranhss.SAAS.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private  AuthService authService;

    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {

        AuthResponse auth = authService.login(request);

        // ✅ Set JWT in HttpOnly cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", auth.getAccessToken())
                .httpOnly(true)
                .secure(false)        // ✅ localhost
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Lax")      // ✅ allow cross-port
                .build();

//        ResponseCookie cookie = ResponseCookie.from("jwt", auth.getAccessToken())
//                .httpOnly(true)
//                .secure(true) // use true in production (HTTPS)
//                .path("/")
//                .maxAge(7 * 24 * 60 * 60) // 7 days
//                .sameSite("Strict")
//                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // ✅ Optionally, return minimal info in body (without token)
        return ResponseEntity.ok(Map.of(
                "email", auth.getEmail(),
                "role", auth.getRole()
        ));
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
//        String jwt = authHeader.substring(7);
//        authService.logout(jwt);
//        return ResponseEntity.ok("Logged out successfully");
//    }


//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletResponse response) {
//        ResponseCookie cookie = ResponseCookie.from("jwt", "")
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(0)
//                .sameSite("Strict")
//                .build();
//
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
//    }


//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletResponse response) {
//
//        // ✅ Clear JWT cookie
//        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
//                .httpOnly(true)
//                .secure(false)       // ⚠️ localhost: must be false
//                .path("/")
//                .maxAge(0)
//                .sameSite("Lax")
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
//
//        // ✅ Clear CSRF cookie (Angular reads this one)
//        ResponseCookie csrfCookie = ResponseCookie.from("XSRF-TOKEN", "")
//                .httpOnly(false)    // ⚠️ must be readable by Angular
//                .secure(false)
//                .path("/")
//                .maxAge(0)
//                .sameSite("Lax")
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, csrfCookie.toString());
//
//        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
//    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response) {

        // 1️⃣ Get JWT from cookie
        String jwt = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                }
            }
        }

        // 2️⃣ Revoke token in DB
        if (jwt != null) {
            tokenRepository.findAllByToken(jwt).forEach(token -> {
                token.setRevoked(true);
                token.setExpired(true);
            });
            tokenRepository.saveAll(tokenRepository.findAllByToken(jwt));
        }

        // 3️⃣ Delete JWT cookie
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // 4️⃣ Delete CSRF cookie
        ResponseCookie csrfCookie = ResponseCookie.from("XSRF-TOKEN", "")
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, csrfCookie.toString());

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }


    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> authCheck(@AuthenticationPrincipal User principal) {
        if (principal == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(Map.of(
                "email", principal.getEmail(),
                "role", principal.getRole()
        ));
    }




}

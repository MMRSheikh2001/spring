package com.emranhss.SAAS.service;

import com.emranhss.SAAS.dto.request.LoginRequest;
import com.emranhss.SAAS.dto.request.RegisterRequest;
import com.emranhss.SAAS.dto.response.AuthResponse;

import com.emranhss.SAAS.entity.Role;
import com.emranhss.SAAS.entity.Token;
import com.emranhss.SAAS.entity.TokenType;
import com.emranhss.SAAS.entity.User;
import com.emranhss.SAAS.exception.EmailAlreadyExistsException;
import com.emranhss.SAAS.repository.TokenRepository;
import com.emranhss.SAAS.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.expiration}")
    private long jwtExpiration;


    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ================= REGISTER =================
    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setActive(true);

        User savedUser = userRepository.save(user);

        // Generate JWT tokens
        String accessToken = jwtService.generateToken(savedUser);
        String refreshToken = generateRefreshToken(savedUser);

        // Save access token
        saveUserToken(savedUser, accessToken);



        return new AuthResponse(
                accessToken,
                "Bearer",
                jwtExpiration,
                user.getEmail(),
                user.getRole().name()
        );

    }


    // ================= LOGIN =================
    @Override
    public AuthResponse login(LoginRequest request) {
        // Authenticate credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Revoke old tokens
        revokeAllUserTokens(user);

        // Generate new tokens
        String accessToken = jwtService.generateToken(user);
        String refreshToken = generateRefreshToken(user);

        saveUserToken(user, accessToken);




        return new AuthResponse(
                accessToken,
                "Bearer",
                86400000L,
                user.getEmail(),
                user.getRole().name()
        );
    }


    // ================= REFRESH TOKEN =================
    @Override
    public AuthResponse refreshToken(String refreshToken) {
        Token token = tokenRepository.findAllByToken(refreshToken)
                .stream()
                .filter(t -> !t.isRevoked())
                .filter(t -> t.getTokenType() == TokenType.REFRESH)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Refresh token not found or revoked"));


        if (token.isRevoked() || token.getTokenType() != TokenType.REFRESH) {
            throw new RuntimeException("Invalid refresh token");
        }

        User user = token.getUser();

        // Generate new access token
        String newAccessToken = jwtService.generateToken(user);

        // Revoke old tokens
        revokeAllUserTokens(user);

        // Save new access token
        saveUserToken(user, newAccessToken);

        // Optionally generate new refresh token
        String newRefreshToken = generateRefreshToken(user);


        return new AuthResponse(
                newAccessToken,
                "Bearer",
                86400000L,
                user.getEmail(),
                user.getRole().name()
        );
    }

    // ================= LOGOUT =================
    @Override
    public void logout(String jwt) {
        Token token = tokenRepository.findAllByToken(jwt)
                .stream()
                .filter(t -> !t.isRevoked())
                .filter(t -> t.getTokenType() == TokenType.REFRESH)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Refresh token not found or revoked"));


        token.setRevoked(true);
        tokenRepository.save(token);
    }

    // ================= HELPER METHODS =================

    // Save new access token in DB
    private void saveUserToken(User user, String jwt) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwt);
        token.setTokenType(TokenType.ACCESS);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    // Revoke all old tokens for user

    private void revokeAllUserTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllValidTokensByUser(user.getId());

        if (validTokens.isEmpty()) return;

        validTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validTokens);
    }



//    private void revokeAllUserTokens(User user) {
//        List<Token> validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
//        if (!validTokens.isEmpty()) {
//            validTokens.forEach(t -> t.setRevoked(true));
//            tokenRepository.saveAll(validTokens);
//        }
//    }

    // Generate refresh token
    private String generateRefreshToken(User user) {
        String refreshToken = jwtService.generateToken(new HashMap<>(), user);
        Token token = new Token();
        token.setUser(user);
        token.setToken(refreshToken);
        token.setTokenType(TokenType.REFRESH);
        token.setRevoked(false);
        tokenRepository.save(token);
        return refreshToken;
    }

}

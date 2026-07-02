package com.emranhss.SAAS.service;

import com.emranhss.SAAS.dto.request.LoginRequest;
import com.emranhss.SAAS.dto.request.RegisterRequest;
import com.emranhss.SAAS.dto.response.AuthResponse;


public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String refreshToken);
    void logout(String jwt);

}

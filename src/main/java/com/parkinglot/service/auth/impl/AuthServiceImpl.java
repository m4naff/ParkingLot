package com.parkinglot.service.auth.impl;

import com.parkinglot.payload.request.auth.LoginRequest;
import com.parkinglot.payload.request.auth.SignupRequest;
import com.parkinglot.payload.request.auth.TokenRefreshRequest;
import com.parkinglot.payload.response.auth.JWTResponse;
import com.parkinglot.payload.response.auth.TokenRefreshResponse;
import com.parkinglot.security.repository.UserRepository;
import com.parkinglot.service.auth.AuthService;
import com.parkinglot.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class named {@link AuthServiceImpl} that provides authentication-related functionalities.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    @Override
    public String register(SignupRequest request) {
        return "";
    }

    @Override
    public JWTResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        return null;
    }

    @Override
    public String logout(String token) {
        return "";
    }
}

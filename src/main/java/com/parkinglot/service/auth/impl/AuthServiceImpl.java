package com.parkinglot.service.auth.impl;

import com.parkinglot.exception.user.EmailAlreadyExistsException;
import com.parkinglot.payload.request.auth.LoginRequest;
import com.parkinglot.payload.request.auth.SignupRequest;
import com.parkinglot.payload.request.auth.TokenRefreshRequest;
import com.parkinglot.payload.response.auth.JWTResponse;
import com.parkinglot.payload.response.auth.TokenRefreshResponse;
import com.parkinglot.security.jwt.JwtUtils;
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

    private final JwtUtils jwtUtils;

    /**
     * Registers a new user.
     *
     * @param request the signup request
     * @return the generated user ID
     */
    @Override
    public String register(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
           throw new EmailAlreadyExistsException(request.getEmail());
        }
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

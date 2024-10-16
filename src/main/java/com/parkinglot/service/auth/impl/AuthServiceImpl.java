package com.parkinglot.service.auth.impl;

import com.parkinglot.exception.user.EmailAlreadyExistsException;
import com.parkinglot.exception.user.RefreshTokenNotFoundException;
import com.parkinglot.exception.user.UserNotFoundException;
import com.parkinglot.payload.request.auth.LoginRequest;
import com.parkinglot.payload.request.auth.SignupRequest;
import com.parkinglot.payload.request.auth.TokenRefreshRequest;
import com.parkinglot.payload.response.auth.JWTResponse;
import com.parkinglot.payload.response.auth.TokenRefreshResponse;
import com.parkinglot.security.CustomUserDetails;
import com.parkinglot.security.jwt.JwtUtils;
import com.parkinglot.security.model.entity.RefreshToken;
import com.parkinglot.security.model.entity.UserEntity;
import com.parkinglot.security.repository.UserRepository;
import com.parkinglot.service.auth.AuthService;
import com.parkinglot.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .userName(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        userRepository.save(userEntity);
        return "Success";
    }

    /**
     * Authenticates a user.
     *
     * @param request the login request
     * @return the JWT response
     */
    @Override
    public JWTResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());

        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtUtils.generateJwtToken(auth);

        UserEntity userEntity = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        return JWTResponse.builder()
                .email(request.getEmail())
                .token(jwtToken)
                .refreshToken(refreshTokenService.createRefreshToken(userEntity))
                .build();
    }

    /**
     * Refreshes the JWT token.
     *
     * @param request the token refresh request
     * @return the token refresh response
     */
    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
                .orElseThrow(RefreshTokenNotFoundException::new);

        if (!refreshTokenService.isRefreshExpired(refreshToken)){
            CustomUserDetails customUserDetails = new CustomUserDetails(refreshToken.getUserEntity());
            String newToken = jwtUtils.generateJwtToken(customUserDetails);

            return TokenRefreshResponse.builder()
                    .accessToken(newToken)
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
        return null;
    }

    /**
     * Logs out the user.
     *
     * @param token the user's token
     * @return the result of the logout operation
     */
    @Override
    public String logout(String token) {
        String authToken = jwtUtils.extractJwtTokenFromHeader(token);

        if (jwtUtils.validateJwtToken(authToken)) {
            String id = jwtUtils.getIdFromToken(authToken);

            refreshTokenService.deleteByUserId(id);

            return "Success";
        }

        return "Failed";
    }
}

package com.parkinglot.service.auth.impl;


import com.parkinglot.security.model.entity.RefreshToken;
import com.parkinglot.security.model.entity.UserEntity;
import com.parkinglot.security.repository.RefreshTokenRepository;
import com.parkinglot.service.auth.RefreshTokenService;
import com.parkinglot.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class named {@link RefreshTokenServiceImpl} that provides refresh token-related functionalities.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpireMs}")
    private Long expireSeconds;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserService userService;


    /**
     * Creates a refresh token for the specified user.
     *
     * @param userEntity the user entity
     * @return the generated refresh token
     */
    @Override
    public String createRefreshToken(UserEntity userEntity) {
        RefreshToken token = getByUser(userEntity.getId());

        if (token == null) {
           token = new RefreshToken();
           token.setUserEntity(userEntity);
        }

        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(expireSeconds).atZone(ZoneOffset.UTC).toLocalDate());
        refreshTokenRepository.save(token);

        return token.getToken();
    }

    /**
     * Checks if the refresh token is expired.
     *
     * @param token the refresh token
     * @return {@code true} if the token is expired, {@code false} otherwise
     */
    @Override
    public boolean isRefreshExpired(RefreshToken token) {
       return token.getExpiryDate().isBefore(LocalDate.now());
    }

    /**
     * Retrieves the refresh token associated with the specified user.
     *
     * @param userId the user ID
     * @return the refresh token
     */
    @Override
    public RefreshToken getByUser(String userId) {
       return refreshTokenRepository.findByUserEntityId(userId);
    }

    /**
     * Retrieves the refresh token by its token value.
     *
     * @param token the token value
     * @return an {@link Optional} containing the refresh token, or empty if not found
     */
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    /**
     * Deletes the refresh token associated with the specified user.
     *
     * @param userId the user ID
     * @return the number of refresh tokens deleted
     */
    @Override
    public int deleteByUserId(String userId) {
        UserEntity userEntity = userService.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Could not find user:" + userId));
       return refreshTokenRepository.deleteByUserEntity(userEntity);
    }
}

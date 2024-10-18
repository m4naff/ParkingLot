package com.parkinglot.service.user.impl;

import com.parkinglot.exception.user.UserNotFoundException;
import com.parkinglot.model.User;
import com.parkinglot.model.mapper.user.UserEntityToUserMapper;
import com.parkinglot.security.model.entity.UserEntity;
import com.parkinglot.service.auth.UserService;
import com.parkinglot.service.user.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation class named {@link UserGetServiceImpl} for getting user.
 */
@Service
@RequiredArgsConstructor
public class UserGetServiceImpl implements UserGetService {

    private final UserService userService;

    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();

    /**
     * Retrieves a user by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the user
     * @throws UserNotFoundException if the user with the given ID is not found
     */
    @Override
    public User getUserById(String userId) {
        final UserEntity userEntity = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userEntityToUserMapper.map(userEntity);
    }

    /**
     * Retrieves an admin user by admin ID.
     *
     * @param adminId the ID of the admin user to retrieve
     * @return the admin user
     * @throws UserNotFoundException if the admin user with the given ID is not found
     */
    @Override
    public User getAdminById(String adminId) {
        final UserEntity userEntity = userService.findById(adminId)
                .orElseThrow(() -> new UserNotFoundException(adminId));

        return userEntityToUserMapper.map(userEntity);
    }
}

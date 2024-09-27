package com.kh.sbilyhour.users_module.infrastructure.service;

import com.kh.sbilyhour.common_module.infrastructure.config.DateTimeConfig;
import com.kh.sbilyhour.users_module.application.dto.register_user.RegisterUserRequest;
import com.kh.sbilyhour.users_module.application.dto.UserDTO;
import com.kh.sbilyhour.users_module.domain.entities.User;
import com.kh.sbilyhour.users_module.infrastructure.mapper.RegisterUserMapper;
import com.kh.sbilyhour.users_module.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user registration.
 * <P>
 * This class handles the registration of users, including checking for
 * existing users and mapping between DTOs and entity objects.
 * </P>
 */
@Service
public class RegisterUserService {

    private final UserJpaRepository userJpaRepository;
    private final RegisterUserMapper userMapper;
    private final DateTimeConfig dateTimeConfig;

    @Autowired
    public RegisterUserService(UserJpaRepository userJpaRepository, RegisterUserMapper userMapper, DateTimeConfig dateTimeConfig) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
        this.dateTimeConfig = dateTimeConfig;
    }

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param request the registration request containing user details
     * @return the DTO representation of the registered user
     * @throws RuntimeException if a user with the same full name already exists
     */
    public UserDTO registerUser(RegisterUserRequest request) {
        // Check if the user already exists
        if (isExitingUser(request.getFullName())) {
            throw new RuntimeException("User with full name " + request.getFullName() + " already exists.");
        }

        // Convert request to User entity
        User user = userMapper.toEntity(request);
        user.setCreateDate(dateTimeConfig.currentFormattedDate()); // Set creation date

        // Save the user entity
        User savedUser = userJpaRepository.save(user);

        // Convert saved User entity back to UserDTO and return
        return userMapper.toDTO(savedUser);
    }

    /**
     * Checks if a user with the given full name already exists.
     *
     * @param fullName the full name of the user to check
     * @return true if the user exists, false otherwise
     */
    public Boolean isExitingUser(String fullName) {
       return userJpaRepository.findByFullName(fullName).isPresent();
    }

}

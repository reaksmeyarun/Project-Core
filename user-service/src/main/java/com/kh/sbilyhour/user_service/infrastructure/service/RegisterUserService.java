package com.kh.sbilyhour.user_service.infrastructure.service;

import com.kh.sbilyhour.core.infrastructure.config.DateTimeConfig;
import com.kh.sbilyhour.user_service.domain.entities.User;
import com.kh.sbilyhour.user_service.infrastructure.mapper.RegisterUserMapper;
import com.kh.sbilyhour.user_service.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for user registration.
 * <p>
 * This class handles the registration of users, including checking for
 * existing users and mapping between DTOs and entity objects.
 * </P>
 */
@Service
public class RegisterUserService {

    private final UserJpaRepository userJpaRepository;
    private final DateTimeConfig dateTimeConfig;

    @Autowired
    public RegisterUserService(UserJpaRepository userJpaRepository, RegisterUserMapper userMapper, DateTimeConfig dateTimeConfig) {
        this.userJpaRepository = userJpaRepository;
        this.dateTimeConfig = dateTimeConfig;
    }

    /**
     * Registers a new user by saving their information in the database.
     *
     * <p>This method sets the user's creation date to the current date and then
     * saves the user entity using the JPA repository.</p>
     *
     * @param user The user object containing the details of the user to be registered.
     * @return The saved {@link User} entity, including any auto-generated fields (such as ID).
     */
    public User registerUser(User user) {
        // Convert request to User entity
        user.setCreateDate(dateTimeConfig.currentFormattedDate()); // Set creation date

        // Save the user entity
        return userJpaRepository.save(user);
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

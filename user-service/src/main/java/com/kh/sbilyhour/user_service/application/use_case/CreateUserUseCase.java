package com.kh.sbilyhour.user_service.application.use_case;

import com.kh.sbilyhour.core.infrastructure.exception.UserAlreadyExitException;
import com.kh.sbilyhour.user_service.adapters.dto.register_user.RegisteredUserWebDTO;
import com.kh.sbilyhour.user_service.application.dto.UserApplicationDTO;
import com.kh.sbilyhour.user_service.application.dto.register_user.RegistrationUserRequest;
import com.kh.sbilyhour.user_service.domain.entities.User;
import com.kh.sbilyhour.user_service.infrastructure.mapper.RegisterUserMapper;
import com.kh.sbilyhour.user_service.infrastructure.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use case for creating a new user.
 * <p>
 * This class encapsulates the logic for user registration,
 * including checking for existing users and delegating
 * the actual registration process to the RegisterUserService.
 * </p>
 */
@Service
public class CreateUserUseCase {

    private final RegisterUserService registerUserService;
    private final RegisterUserMapper mapper;

    @Autowired
    public CreateUserUseCase(RegisterUserService registerUserService, RegisterUserMapper mapper) {
        this.registerUserService = registerUserService;
        this.mapper = mapper;
    }

    /**
     * Executes the user registration process.
     *
     * <p>This method handles the registration of a new user by performing the following steps:</p>
     * <ol>
     *     <li>Converts the incoming {@link RegistrationUserRequest} DTO to an
     *         {@link UserApplicationDTO}.</li>
     *     <li>Checks if a user with the same full name already exists in the system.
     *         If the user exists, a {@link UserAlreadyExitException} is thrown.</li>
     *     <li>Converts the {@link UserApplicationDTO} to a {@link User} entity.</li>
     *     <li>Saves the new user entity in the database.</li>
     *     <li>Converts the saved {@link User} entity to a {@link RegisteredUserWebDTO} for
     *         web-based data transfer.</li>
     * </ol>
     *
     * @param request The registration request containing user details.
     * @return The corresponding {@link RegisteredUserWebDTO} after successful registration.
     * @throws UserAlreadyExitException if a user with the same full name already exists.
     */
    public RegisteredUserWebDTO execute(RegistrationUserRequest request) {
        // 1. Convert request DTO to application DTO
        UserApplicationDTO userApplicationDTO = mapper.toApplicationDTO(request);

        // 2. Business logic: Check if user already exists
        String findFullName = userApplicationDTO.getFullName();
        boolean isExitingUser = registerUserService.isExitingUser(findFullName);
        if (isExitingUser) {
            throw new UserAlreadyExitException();
        }

        // 3. Convert Application DTO to Entity
        User user = mapper.toEntity(userApplicationDTO);

        // 4. Save user in the database
        User savedUser = registerUserService.registerUser(user);

        // 5. Convert the saved Entity to Web DTO
        return mapper.toWebDTO(savedUser);
    }

}
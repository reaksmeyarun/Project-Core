package com.kh.sbilyhour.users_module.application.use_case;

import com.kh.sbilyhour.users_module.application.dto.register_user.RegisterUserRequest;
import com.kh.sbilyhour.users_module.application.dto.UserDTO;
import com.kh.sbilyhour.users_module.infrastructure.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Use case for creating a new user.
 * <p>
 * This class encapsulates the logic for user registration,
 * including checking for existing users and delegating
 * the actual registration process to the RegisterUserService.
 * </p>
 */
@Component
public class CreateUserUseCase {

    private final RegisterUserService registerUserService;

    @Autowired
    public CreateUserUseCase(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    /**
     * Executes the user creation process.
     *
     * @param request the user registration request containing user details
     * @return the registered UserDTO
     * @throws RuntimeException if a user with the same name already exists
     */
    public UserDTO execute(RegisterUserRequest request) {
        if (registerUserService.isExitingUser(request.getFullName())) {
            throw new RuntimeException("User with the same name already exists.");
        }

        return registerUserService.registerUser(request);
    }
}
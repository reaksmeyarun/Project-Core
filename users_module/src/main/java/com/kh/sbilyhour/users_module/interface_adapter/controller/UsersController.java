package com.kh.sbilyhour.users_module.interface_adapter.controller;

import com.kh.sbilyhour.common_module.domain.model.ResponseWrapper;
import com.kh.sbilyhour.users_module.application.dto.register_user.RegisterUserRequest;
import com.kh.sbilyhour.users_module.application.dto.register_user.RegisterUserResponse;
import com.kh.sbilyhour.users_module.application.use_case.CreateUserUseCase;
import com.kh.sbilyhour.users_module.application.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * Controller for managing user-related operations.
 * <p>
 * This class handles incoming requests for user creation and delegates
 * the business logic to the appropriate use case.
 * </p>
 */
@RestController
@RequestMapping("/core/auth/v1")
@Validated  // Ensure that validation is applied at the controller level
public class UsersController {

    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public UsersController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    /**
     * Handles the creation of a new user.
     *
     * @param request the request object containing user details
     * @return a ResponseEntity containing the response wrapper with user details
     */
    @PostMapping("/createUser")
    public ResponseEntity<ResponseWrapper<RegisterUserResponse>> createUser(@Valid @RequestBody RegisterUserRequest request) {
        // Create the user
        UserDTO registeredUser = createUserUseCase.execute(request);

        // Build the response object
        RegisterUserResponse registerUserResponse = RegisterUserResponse.builder()
                .user(registeredUser)
                .build();

        // Wrap the response
        ResponseWrapper<RegisterUserResponse> response = ResponseWrapper.<RegisterUserResponse>builder()
                .data(registerUserResponse)
                .build();

        // Return the response entity
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

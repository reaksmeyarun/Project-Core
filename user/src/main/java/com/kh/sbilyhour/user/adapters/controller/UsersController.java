package com.kh.sbilyhour.user.adapters.controller;

import com.kh.sbilyhour.user.adapters.dto.register_user.RegisteredUserWebDTO;
import com.kh.sbilyhour.user.application.dto.register_user.RegistrationUserRequest;
import com.kh.sbilyhour.user.application.use_case.CreateUserUseCase;
import com.kh.sbilyhour.core.domain.model.ResponseWrapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related operations.
 * <p>
 * This class handles incoming requests for user creation and delegates
 * the business logic to the appropriate use case.
 * </p>
 */
@RestController
@RequestMapping("/user/v1")
@Validated
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
    @PostMapping("/registerNewUser")
    public ResponseEntity<ResponseWrapper<RegisteredUserWebDTO>> registerNewUser(@Valid @RequestBody RegistrationUserRequest request) {
        RegisteredUserWebDTO registeredUserWebDTO = createUserUseCase.execute(request);
        ResponseWrapper<RegisteredUserWebDTO> response = ResponseWrapper.<RegisteredUserWebDTO>builder()
                .data(registeredUserWebDTO)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

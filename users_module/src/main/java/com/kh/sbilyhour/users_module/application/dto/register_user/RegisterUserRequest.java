package com.kh.sbilyhour.users_module.application.dto.register_user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotEmpty(message = "Full name is require!")
    private String fullName;

    @Pattern(regexp = "^[0-9]{8,9}$", message = "Phone number must be between 8 and 12 digits")
    private String phoneNumber;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
    private String gender;

}
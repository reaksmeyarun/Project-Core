package com.kh.sbilyhour.users_module.application.dto.register_user;

import com.kh.sbilyhour.users_module.application.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {

    private UserDTO user;

}

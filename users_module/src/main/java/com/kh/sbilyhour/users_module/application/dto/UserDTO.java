package com.kh.sbilyhour.users_module.application.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String gender;

}

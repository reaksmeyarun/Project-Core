package com.kh.sbilyhour.user_service.application.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplicationDTO {

    private String fullName;

    private String phoneNumber;

    private String gender;

}

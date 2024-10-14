package com.kh.sbilyhour.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserApplicationDTO {

    private String fullName;

    private String phoneNumber;

    private String gender;

}

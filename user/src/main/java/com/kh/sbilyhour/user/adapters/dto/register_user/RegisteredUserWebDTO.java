package com.kh.sbilyhour.user.adapters.dto.register_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserWebDTO {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private String gender;

    private String createDate;

}
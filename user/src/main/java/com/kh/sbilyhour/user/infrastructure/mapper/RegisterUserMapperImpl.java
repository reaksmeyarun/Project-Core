package com.kh.sbilyhour.user.infrastructure.mapper;

import com.kh.sbilyhour.user.adapters.dto.register_user.RegisteredUserWebDTO;
import com.kh.sbilyhour.user.application.dto.UserApplicationDTO;
import com.kh.sbilyhour.user.application.dto.register_user.RegistrationUserRequest;
import com.kh.sbilyhour.user.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserMapperImpl implements RegisterUserMapper {

    public UserApplicationDTO toApplicationDTO(RegistrationUserRequest request) {
        if (request == null) {
            return null;
        } else {
            UserApplicationDTO userApplicationDTO = new UserApplicationDTO();
            userApplicationDTO.setFullName(request.getFullName());
            userApplicationDTO.setPhoneNumber(request.getPhoneNumber());
            userApplicationDTO.setGender(request.getGender());
            return userApplicationDTO;
        }
    }

    public User toEntity(UserApplicationDTO userApplicationDTO) {
        if (userApplicationDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setFullName(userApplicationDTO.getFullName());
            user.setPhoneNumber(userApplicationDTO.getPhoneNumber());
            user.setGender(userApplicationDTO.getGender());
            return user;
        }
    }

    public RegisteredUserWebDTO toWebDTO(User user) {
        if (user == null) {
            return null;
        } else {
            RegisteredUserWebDTO registeredUserWebDTO = new RegisteredUserWebDTO();
            registeredUserWebDTO.setId(user.getId());
            registeredUserWebDTO.setFullName(user.getFullName());
            registeredUserWebDTO.setPhoneNumber(user.getPhoneNumber());
            registeredUserWebDTO.setGender(user.getGender());
            registeredUserWebDTO.setCreateDate(user.getCreateDate());
            return registeredUserWebDTO;
        }
    }
}

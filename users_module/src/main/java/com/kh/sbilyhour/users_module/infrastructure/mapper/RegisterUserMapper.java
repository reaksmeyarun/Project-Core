package com.kh.sbilyhour.users_module.infrastructure.mapper;

import com.kh.sbilyhour.users_module.application.dto.register_user.RegisterUserRequest;
import com.kh.sbilyhour.users_module.application.dto.UserDTO;
import com.kh.sbilyhour.users_module.domain.entities.User;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between User entity and User DTOs.
 * <p>
 * This interface uses MapStruct for generating the implementation to map
 * User entities to UserDTOs and vice versa.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO
     */
    UserDTO toDTO(User user);

    /**
     * Converts a RegisterUserRequest to a User entity.
     *
     * @param request the RegisterUserRequest containing user information
     * @return the corresponding User entity
     */
    User toEntity(RegisterUserRequest request);
}

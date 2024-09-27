package com.kh.sbilyhour.users_module.domain.repository;

import com.kh.sbilyhour.users_module.domain.entities.User;

import java.util.Optional;

/**
 * Repository interface for accessing User entities.
 * <p>
 * This interface provides methods for querying User data in the database.
 * It is recommended to be used in conjunction with Spring Data JPA.
 * </p>
 */
public interface UserRepository {

    /**
     * Finds a User by their full name.
     *
     * @param fullName the full name of the user
     * @return an Optional containing the User if found, or empty if not
     */
    Optional<User> findByFullName(String fullName);
}
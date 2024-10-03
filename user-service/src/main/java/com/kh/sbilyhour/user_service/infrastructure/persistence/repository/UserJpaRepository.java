package com.kh.sbilyhour.user_service.infrastructure.persistence.repository;

import com.kh.sbilyhour.user_service.domain.entities.User;
import com.kh.sbilyhour.user_service.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing User entities in the database.
 * <P>
 * This interface extends JpaRepository to provide basic CRUD operations
 * and also extends UserRepository for custom query methods.
 * </P>
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository { }
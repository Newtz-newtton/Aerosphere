package com.aerosphere.auth.repository;

import com.aerosphere.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Purpose:
 * Provides database operations for User entities.
 *
 * Responsibilities:
 * - Performs CRUD operations.
 * - Retrieves users by email.
 * - Supports authentication queries.
 *
 * Module:
 * Authentication
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
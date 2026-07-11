package com.aerosphere.aircraft.repository;

import com.aerosphere.aircraft.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Aircraft entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support aircraft lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Aircraft
 */
@Repository
public interface AircraftRepository
        extends JpaRepository<Aircraft, Long> {

    boolean existsByRegistrationNumber(String registrationNumber);

    Optional<Aircraft> findByRegistrationNumber(String registrationNumber);

}
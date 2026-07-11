package com.aerosphere.airline.repository;

import com.aerosphere.airline.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Airline entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support airline lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Airline
 */
@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    boolean existsByAirlineCode(String airlineCode);

    Optional<Airline> findByAirlineCode(String airlineCode);

}
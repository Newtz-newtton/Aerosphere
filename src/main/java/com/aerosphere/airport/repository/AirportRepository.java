package com.aerosphere.airport.repository;

import com.aerosphere.airport.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Airport entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support airport lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Airport
 */
@Repository
public interface AirportRepository
        extends JpaRepository<Airport, Long> {

    boolean existsByAirportCode(String airportCode);

    Optional<Airport> findByAirportCode(String airportCode);

}
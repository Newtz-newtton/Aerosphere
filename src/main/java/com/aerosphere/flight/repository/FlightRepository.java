package com.aerosphere.flight.repository;

import com.aerosphere.flight.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Flight entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support flight lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Flight
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    boolean existsByFlightNumber(String flightNumber);

    Optional<Flight> findByFlightNumber(String flightNumber);

    @Query("""
            SELECT f
            FROM Flight f
            JOIN FETCH f.airline
            JOIN FETCH f.aircraft
            JOIN FETCH f.departureAirport
            JOIN FETCH f.arrivalAirport
            """)
    List<Flight> findAllWithRelationships();

    @Query("""
            SELECT f
            FROM Flight f
            JOIN FETCH f.airline
            JOIN FETCH f.aircraft
            JOIN FETCH f.departureAirport
            JOIN FETCH f.arrivalAirport
            WHERE f.id = :id
            """)
    Optional<Flight> findByIdWithRelationships(@Param("id") Long id);

}
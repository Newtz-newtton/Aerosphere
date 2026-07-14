package com.aerosphere.booking.repository;

import com.aerosphere.booking.entity.Booking;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Booking entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support booking lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Booking
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByBookingReference(String bookingReference);

    Optional<Booking> findByBookingReference(String bookingReference);

    @Query("""
            SELECT b
            FROM Booking b
            JOIN FETCH b.user
            JOIN FETCH b.flight
            """)
    List<Booking> findAllWithRelationships();

    @Query("""
            SELECT b
            FROM Booking b
            JOIN FETCH b.user
            JOIN FETCH b.flight
            WHERE b.id = :id
            """)
    Optional<Booking> findByIdWithRelationships(@Param("id") Long id);

    @Query("""
        SELECT b
        FROM Booking b
        JOIN FETCH b.user
        JOIN FETCH b.flight
        WHERE b.id = :id
        """)
    Optional<Booking> findByIdWithUserAndFlight(
            @Param("id") Long id);

}
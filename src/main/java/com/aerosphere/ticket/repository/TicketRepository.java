package com.aerosphere.ticket.repository;

import com.aerosphere.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Ticket entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support ticket lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Ticket
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsByTicketNumber(String ticketNumber);

    Optional<Ticket> findByTicketNumber(String ticketNumber);

    Optional<Ticket> findByBookingId(Long bookingId);

    @Query("""
        SELECT t
        FROM Ticket t
        JOIN FETCH t.booking b
        JOIN FETCH b.user
        JOIN FETCH b.flight
        JOIN FETCH t.payment
        """)
    List<Ticket> findAllWithRelationships();

    @Query("""
        SELECT t
        FROM Ticket t
        JOIN FETCH t.booking b
        JOIN FETCH b.user
        JOIN FETCH b.flight
        JOIN FETCH t.payment
        WHERE t.id = :id
        """)
    Optional<Ticket> findByIdWithRelationships(@Param("id") Long id);



}
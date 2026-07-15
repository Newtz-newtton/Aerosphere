package com.aerosphere.checkin.repository;

import com.aerosphere.checkin.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for CheckIn entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support check-in lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Check-in
 */
@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    boolean existsByTicketId(Long ticketId);

    Optional<CheckIn> findByTicketId(Long ticketId);

    @Query("""
            SELECT c
            FROM CheckIn c
            JOIN FETCH c.ticket
            """)
    List<CheckIn> findAllWithRelationships();

    @Query("""
            SELECT c
            FROM CheckIn c
            JOIN FETCH c.ticket
            WHERE c.id = :id
            """)
    Optional<CheckIn> findByIdWithRelationships(
            @Param("id") Long id);

}
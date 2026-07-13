package com.aerosphere.payment.repository;

import com.aerosphere.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Purpose:
 * Provides database access operations for Payment entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Support payment lookup operations.
 * - Provide custom database queries.
 *
 * Module:
 * Payment
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByPaymentReference(String paymentReference);

    Optional<Payment> findByPaymentReference(String paymentReference);

    boolean existsByBookingId(Long bookingId);

    Optional<Payment> findByBookingId(Long bookingId);

    @Query("""
            SELECT p
            FROM Payment p
            JOIN FETCH p.booking
            """)
    List<Payment> findAllWithRelationships();

    @Query("""
            SELECT p
            FROM Payment p
            JOIN FETCH p.booking
            WHERE p.id = :id
            """)
    Optional<Payment> findByIdWithRelationships(@Param("id") Long id);

}
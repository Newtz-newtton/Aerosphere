package com.aerosphere.booking.entity;

import com.aerosphere.auth.entity.User;
import com.aerosphere.common.entity.BaseEntity;
import com.aerosphere.flight.entity.Flight;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a customer's flight booking.
 *
 * Responsibilities:
 * - Stores booking information.
 * - Links a customer to a flight.
 * - Maintains booking lifecycle status.
 * - Stores booking reference and fare details.
 *
 * Module:
 * Booking
 */
@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    private String bookingReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalFare;

    @Column(nullable = false)
    private LocalDateTime bookingDate;
}
package com.aerosphere.ticket.entity;

import com.aerosphere.booking.entity.Booking;
import com.aerosphere.common.entity.BaseEntity;
import com.aerosphere.payment.entity.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents an issued airline ticket.
 *
 * Responsibilities:
 * - Store ticket information.
 * - Associate an issued ticket with a booking and payment.
 * - Maintain ticket lifecycle status.
 * - Store immutable passenger information at the time of ticket generation.
 *
 * Module:
 * Ticket
 */
@Entity
@Table(
        name = "tickets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_ticket_number",
                        columnNames = "ticket_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "ticket_number",
            nullable = false,
            unique = true,
            length = 30
    )
    private String ticketNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "booking_id",
            nullable = false,
            unique = true
    )
    private Booking booking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "payment_id",
            nullable = false,
            unique = true
    )
    private Payment payment;

    @Column(
            name = "passenger_name",
            nullable = false,
            length = 150
    )
    private String passengerName;

    @Column(
            name = "flight_number",
            nullable = false,
            length = 10
    )
    private String flightNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus ticketStatus;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

}
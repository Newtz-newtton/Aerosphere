package com.aerosphere.checkin.entity;

import com.aerosphere.common.entity.BaseEntity;
import com.aerosphere.ticket.entity.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a passenger check-in record.
 *
 * Responsibilities:
 * - Store check-in information.
 * - Associate a check-in with a ticket.
 * - Preserve passenger and flight snapshots.
 * - Maintain check-in lifecycle.
 *
 * Module:
 * Check-in
 */
@Entity
@Table(
        name = "check_ins",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_checkin_ticket",
                        columnNames = "ticket_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckIn extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ticket_id",
            nullable = false,
            unique = true
    )
    private Ticket ticket;

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
    private CheckInStatus checkInStatus;

    @Column(nullable = false)
    private LocalDateTime checkedInAt;

}
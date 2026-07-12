package com.aerosphere.flight.entity;

import com.aerosphere.aircraft.entity.Aircraft;
import com.aerosphere.airline.entity.Airline;
import com.aerosphere.airport.entity.Airport;
import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a scheduled flight in the AeroSphere system.
 *
 * Responsibilities:
 * - Store flight schedule information.
 * - Associate airlines, aircraft and airports.
 * - Act as the primary operational entity for bookings.
 *
 * Module:
 * Flight
 */
@Entity
@Table(
        name = "flights",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_flight_number",
                        columnNames = "flight_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "flight_number",
            nullable = false,
            length = 10
    )
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "airline_id",
            nullable = false
    )
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "aircraft_id",
            nullable = false
    )
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "departure_airport_id",
            nullable = false
    )
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "arrival_airport_id",
            nullable = false
    )
    private Airport arrivalAirport;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

}
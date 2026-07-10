package com.aerosphere.airport.entity;

import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Purpose:
 * Represents an airport within the AeroSphere airline management system.
 *
 * Responsibilities:
 * - Stores airport master data.
 * - Maintains airport operational information.
 * - Serves as the source and destination reference for flights.
 *
 * Module:
 * Airport
 */
@Entity
@Table(
        name = "airports",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_airport_code",
                        columnNames = "airport_code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "airport_code",
            nullable = false,
            length = 3
    )
    private String airportCode;

    @Column(
            name = "airport_name",
            nullable = false,
            length = 100
    )
    private String airportName;

    @Column(
            nullable = false,
            length = 100
    )
    private String city;

    @Column(
            nullable = false,
            length = 100
    )
    private String country;

    @Column(
            nullable = false,
            length = 50
    )
    private String timezone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AirportStatus status;

}
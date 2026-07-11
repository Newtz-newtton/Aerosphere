package com.aerosphere.airline.entity;

import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Purpose:
 * Represents an airline within the AeroSphere airline management system.
 *
 * Responsibilities:
 * - Stores airline master data.
 * - Maintains airline operational information.
 * - Serves as the airline reference for flights.
 *
 * Module:
 * Airline
 */
@Entity
@Table(
        name = "airlines",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_airline_code",
                        columnNames = "airline_code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airline extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "airline_code",
            nullable = false,
            length = 3
    )
    private String airlineCode;

    @Column(
            name = "airline_name",
            nullable = false,
            length = 100
    )
    private String airlineName;

    @Column(
            nullable = false,
            length = 100
    )
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AirlineStatus status;

}
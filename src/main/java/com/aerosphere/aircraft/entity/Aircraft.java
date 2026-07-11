package com.aerosphere.aircraft.entity;

import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Purpose:
 * Represents a physical aircraft in the AeroSphere system.
 *
 * Responsibilities:
 * - Store aircraft master information.
 * - Maintain operational aircraft data.
 * - Serve as the aircraft reference for future flight scheduling.
 *
 * Module:
 * Aircraft
 */
@Entity
@Table(
        name = "aircraft",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_registration_number",
                        columnNames = "registration_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aircraft extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "registration_number",
            nullable = false,
            length = 15
    )
    private String registrationNumber;

    @Column(
            nullable = false,
            length = 50
    )
    private String manufacturer;

    @Column(
            nullable = false,
            length = 50
    )
    private String model;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AircraftStatus status;

}
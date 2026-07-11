package com.aerosphere.aircraft.dto.response;

import com.aerosphere.aircraft.entity.AircraftStatus;
import lombok.*;

/**
 * Purpose:
 * Represents aircraft information returned to the client.
 *
 * Responsibilities:
 * - Provide aircraft details in API responses.
 * - Hide persistence implementation details.
 *
 * Module:
 * Aircraft
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftResponse {

    private Long id;

    private String registrationNumber;

    private String manufacturer;

    private String model;

    private Integer seatingCapacity;

    private AircraftStatus status;

}
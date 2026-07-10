package com.aerosphere.airport.dto.response;

import com.aerosphere.airport.entity.AirportStatus;
import lombok.*;

/**
 * Purpose:
 * Represents airport information returned to the client.
 *
 * Responsibilities:
 * - Provide airport details in API responses.
 * - Hide internal persistence implementation.
 *
 * Module:
 * Airport
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponse {

    private Long id;

    private String airportCode;

    private String airportName;

    private String city;

    private String country;

    private String timezone;

    private AirportStatus status;

}
package com.aerosphere.airline.dto.response;

import com.aerosphere.airline.entity.AirlineStatus;
import lombok.*;

/**
 * Purpose:
 * Represents airline information returned to the client.
 *
 * Responsibilities:
 * - Provide airline details in API responses.
 * - Hide internal persistence implementation.
 *
 * Module:
 * Airline
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineResponse {

    private Long id;

    private String airlineCode;

    private String airlineName;

    private String country;

    private AirlineStatus status;

}
package com.aerosphere.airport.dto.request;

import com.aerosphere.airport.entity.AirportStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * Purpose:
 * Represents the request payload for creating or updating an airport.
 *
 * Responsibilities:
 * - Receive airport information from the client.
 * - Validate airport input before processing.
 *
 * Module:
 * Airport
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportRequest {

    @NotBlank(message = "Airport code is required.")
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Airport code must contain exactly 3 uppercase letters."
    )
    private String airportCode;

    @NotBlank(message = "Airport name is required.")
    private String airportName;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotBlank(message = "Timezone is required.")
    private String timezone;

    @NotNull(message = "Airport status is required.")
    private AirportStatus status;

}
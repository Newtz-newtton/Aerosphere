package com.aerosphere.airline.dto.request;

import com.aerosphere.airline.entity.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * Purpose:
 * Represents the request payload for creating or updating an airline.
 *
 * Responsibilities:
 * - Receive airline information from the client.
 * - Validate airline input before processing.
 *
 * Module:
 * Airline
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineRequest {

    @NotBlank(message = "Airline code is required.")
    @Pattern(
            regexp = "^[A-Z0-9]{2,3}$",
            message = "Airline code must contain 2 or 3 uppercase letters or digits."
    )
    private String airlineCode;

    @NotBlank(message = "Airline name is required.")
    private String airlineName;

    @NotBlank(message = "Country is required.")
    private String country;

    @NotNull(message = "Airline status is required.")
    private AirlineStatus status;

}
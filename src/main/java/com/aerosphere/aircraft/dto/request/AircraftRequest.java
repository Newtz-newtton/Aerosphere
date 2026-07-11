package com.aerosphere.aircraft.dto.request;

import com.aerosphere.aircraft.entity.AircraftStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Purpose:
 * Represents the request payload for creating or updating an aircraft.
 *
 * Responsibilities:
 * - Receive aircraft information from the client.
 * - Validate aircraft input.
 *
 * Module:
 * Aircraft
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftRequest {

    @NotBlank(message = "Registration number is required.")
    private String registrationNumber;

    @NotBlank(message = "Manufacturer is required.")
    private String manufacturer;

    @NotBlank(message = "Model is required.")
    private String model;

    @NotNull(message = "Seating capacity is required.")
    @Min(value = 1, message = "Seating capacity must be greater than zero.")
    private Integer seatingCapacity;

    @NotNull(message = "Aircraft status is required.")
    private AircraftStatus status;

}
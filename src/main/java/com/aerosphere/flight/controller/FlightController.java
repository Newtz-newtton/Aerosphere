package com.aerosphere.flight.controller;

import com.aerosphere.common.dto.ApiResponse;
import com.aerosphere.flight.dto.request.FlightRequest;
import com.aerosphere.flight.dto.response.FlightResponse;
import com.aerosphere.flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for flight management.
 *
 * Responsibilities:
 * - Accept HTTP requests.
 * - Delegate business logic to FlightService.
 * - Return standardized API responses.
 *
 * Module:
 * Flight
 */
@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<FlightResponse> createFlight(
            @Valid @RequestBody FlightRequest request) {

        FlightResponse response =
                flightService.createFlight(request);

        return ApiResponse.<FlightResponse>builder()
                .success(true)
                .message("Flight created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<FlightResponse>> getAllFlights() {

        List<FlightResponse> response =
                flightService.getAllFlights();

        return ApiResponse.<List<FlightResponse>>builder()
                .success(true)
                .message("Flights retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<FlightResponse> getFlightById(
            @PathVariable Long id) {

        FlightResponse response =
                flightService.getFlightById(id);

        return ApiResponse.<FlightResponse>builder()
                .success(true)
                .message("Flight retrieved successfully.")
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<FlightResponse> updateFlight(
            @PathVariable Long id,
            @Valid @RequestBody FlightRequest request) {

        FlightResponse response =
                flightService.updateFlight(id, request);

        return ApiResponse.<FlightResponse>builder()
                .success(true)
                .message("Flight updated successfully.")
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteFlight(
            @PathVariable Long id) {

        flightService.deleteFlight(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Flight deleted successfully.")
                .build();
    }

}
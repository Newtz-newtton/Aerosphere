package com.aerosphere.airport.controller;

import com.aerosphere.airport.dto.request.AirportRequest;
import com.aerosphere.airport.dto.response.AirportResponse;
import com.aerosphere.airport.service.AirportService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for airport management.
 *
 * Responsibilities:
 * - Handle airport CRUD requests.
 * - Delegate business operations to the service layer.
 * - Return standardized API responses.
 *
 * Module:
 * Airport
 */
@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AirportResponse> createAirport(
            @Valid @RequestBody AirportRequest request) {

        AirportResponse response =
                airportService.createAirport(request);

        return ApiResponse.<AirportResponse>builder()
                .success(true)
                .message("Airport created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<AirportResponse>> getAllAirports() {

        List<AirportResponse> response =
                airportService.getAllAirports();

        return ApiResponse.<List<AirportResponse>>builder()
                .success(true)
                .message("Airports retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AirportResponse> getAirportById(
            @PathVariable Long id) {

        AirportResponse response =
                airportService.getAirportById(id);

        return ApiResponse.<AirportResponse>builder()
                .success(true)
                .message("Airport retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<AirportResponse> updateAirport(
            @PathVariable Long id,
            @Valid @RequestBody AirportRequest request) {

        AirportResponse response =
                airportService.updateAirport(id, request);

        return ApiResponse.<AirportResponse>builder()
                .success(true)
                .message("Airport updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAirport(
            @PathVariable Long id) {

        airportService.deleteAirport(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Airport deleted successfully.")
                .build();
    }

}
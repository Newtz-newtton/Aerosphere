package com.aerosphere.airline.controller;

import com.aerosphere.airline.dto.request.AirlineRequest;
import com.aerosphere.airline.dto.response.AirlineResponse;
import com.aerosphere.airline.service.AirlineService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for airline management.
 *
 * Responsibilities:
 * - Accept HTTP requests.
 * - Delegate business logic to AirlineService.
 * - Return standardized API responses.
 *
 * Module:
 * Airline
 */
@RestController
@RequestMapping("/api/v1/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<AirlineResponse> createAirline(
            @Valid @RequestBody AirlineRequest request) {

        AirlineResponse response =
                airlineService.createAirline(request);

        return ApiResponse.<AirlineResponse>builder()
                .success(true)
                .message("Airline created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<AirlineResponse>> getAllAirlines() {

        List<AirlineResponse> response =
                airlineService.getAllAirlines();

        return ApiResponse.<List<AirlineResponse>>builder()
                .success(true)
                .message("Airlines retrieved successfully.")
                .data(response)
                .build();
    }


    @GetMapping("/{id}")
    public ApiResponse<AirlineResponse> getAirlineById(
            @PathVariable Long id) {

        AirlineResponse response =
                airlineService.getAirlineById(id);

        return ApiResponse.<AirlineResponse>builder()
                .success(true)
                .message("Airline retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<AirlineResponse> updateAirline(
            @PathVariable Long id,
            @Valid @RequestBody AirlineRequest request) {

        AirlineResponse response =
                airlineService.updateAirline(id, request);

        return ApiResponse.<AirlineResponse>builder()
                .success(true)
                .message("Airline updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAirline(
            @PathVariable Long id) {

        airlineService.deleteAirline(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Airline deleted successfully.")
                .build();
    }

}
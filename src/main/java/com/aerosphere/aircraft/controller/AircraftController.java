package com.aerosphere.aircraft.controller;

import com.aerosphere.aircraft.dto.request.AircraftRequest;
import com.aerosphere.aircraft.dto.response.AircraftResponse;
import com.aerosphere.aircraft.service.AircraftService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for aircraft management.
 *
 * Responsibilities:
 * - Accept HTTP requests.
 * - Delegate business logic to AircraftService.
 * - Return standardized API responses.
 *
 * Module:
 * Aircraft
 */
@RestController
@RequestMapping("/api/v1/aircraft")
@RequiredArgsConstructor
public class AircraftController {

    private final AircraftService aircraftService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<AircraftResponse> createAircraft(
            @Valid @RequestBody AircraftRequest request) {

        AircraftResponse response =
                aircraftService.createAircraft(request);

        return ApiResponse.<AircraftResponse>builder()
                .success(true)
                .message("Aircraft created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<AircraftResponse>> getAllAircraft() {

        List<AircraftResponse> response =
                aircraftService.getAllAircraft();

        return ApiResponse.<List<AircraftResponse>>builder()
                .success(true)
                .message("Aircraft retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AircraftResponse> getAircraftById(
            @PathVariable Long id) {

        AircraftResponse response =
                aircraftService.getAircraftById(id);

        return ApiResponse.<AircraftResponse>builder()
                .success(true)
                .message("Aircraft retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<AircraftResponse> updateAircraft(
            @PathVariable Long id,
            @Valid @RequestBody AircraftRequest request) {

        AircraftResponse response =
                aircraftService.updateAircraft(id, request);

        return ApiResponse.<AircraftResponse>builder()
                .success(true)
                .message("Aircraft updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteAircraft(
            @PathVariable Long id) {

        aircraftService.deleteAircraft(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Aircraft deleted successfully.")
                .build();
    }

}
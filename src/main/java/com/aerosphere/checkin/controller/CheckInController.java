package com.aerosphere.checkin.controller;

import com.aerosphere.checkin.dto.request.CheckInRequest;
import com.aerosphere.checkin.dto.response.CheckInResponse;
import com.aerosphere.checkin.service.CheckInService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for passenger check-in management.
 *
 * Responsibilities:
 * - Create check-ins.
 * - Retrieve check-ins.
 * - Update check-ins.
 * - Delete check-ins.
 *
 * Module:
 * Check-in
 */
@RestController
@RequestMapping("/api/v1/checkins")
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ApiResponse<CheckInResponse> createCheckIn(
            @Valid @RequestBody CheckInRequest request) {

        CheckInResponse response =
                checkInService.createCheckIn(request);

        return ApiResponse.<CheckInResponse>builder()
                .success(true)
                .message("Check-in created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','CUSTOMER')")
    public ApiResponse<List<CheckInResponse>> getAllCheckIns() {

        List<CheckInResponse> response =
                checkInService.getAllCheckIns();

        return ApiResponse.<List<CheckInResponse>>builder()
                .success(true)
                .message("Check-ins retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','CUSTOMER')")
    public ApiResponse<CheckInResponse> getCheckInById(
            @PathVariable Long id) {

        CheckInResponse response =
                checkInService.getCheckInById(id);

        return ApiResponse.<CheckInResponse>builder()
                .success(true)
                .message("Check-in retrieved successfully.")
                .data(response)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ApiResponse<CheckInResponse> updateCheckIn(
            @PathVariable Long id,
            @Valid @RequestBody CheckInRequest request) {

        CheckInResponse response =
                checkInService.updateCheckIn(id, request);

        return ApiResponse.<CheckInResponse>builder()
                .success(true)
                .message("Check-in updated successfully.")
                .data(response)
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> deleteCheckIn(
            @PathVariable Long id) {

        checkInService.deleteCheckIn(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Check-in deleted successfully.")
                .build();
    }

}
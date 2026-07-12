package com.aerosphere.booking.controller;

import com.aerosphere.booking.dto.request.BookingRequest;
import com.aerosphere.booking.dto.response.BookingResponse;
import com.aerosphere.booking.service.BookingService;
import com.aerosphere.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for booking management.
 *
 * Responsibilities:
 * - Handle booking CRUD requests.
 * - Delegate business operations to the service layer.
 * - Return standardized API responses.
 *
 * Module:
 * Booking
 */
@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('CUSTOMER','STAFF')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request) {

        BookingResponse response =
                bookingService.createBooking(request);

        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .message("Booking created successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ApiResponse<List<BookingResponse>> getAllBookings() {

        List<BookingResponse> response =
                bookingService.getAllBookings();

        return ApiResponse.<List<BookingResponse>>builder()
                .success(true)
                .message("Bookings retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','CUSTOMER')")
    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> getBookingById(
            @PathVariable Long id) {

        BookingResponse response =
                bookingService.getBookingById(id);

        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .message("Booking retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequest request) {

        BookingResponse response =
                bookingService.updateBooking(id, request);

        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .message("Booking updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Booking deleted successfully.")
                .build();
    }

}
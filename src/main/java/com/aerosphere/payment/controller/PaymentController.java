package com.aerosphere.payment.controller;

import com.aerosphere.common.dto.ApiResponse;
import com.aerosphere.payment.dto.request.PaymentRequest;
import com.aerosphere.payment.dto.response.PaymentResponse;
import com.aerosphere.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for payment management.
 *
 * Responsibilities:
 * - Handle payment CRUD requests.
 * - Delegate business operations to the service layer.
 * - Return standardized API responses.
 *
 * Module:
 * Payment
 */
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasAnyRole('CUSTOMER','STAFF')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.createPayment(request);

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment created successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ApiResponse<List<PaymentResponse>> getAllPayments() {

        List<PaymentResponse> response =
                paymentService.getAllPayments();

        return ApiResponse.<List<PaymentResponse>>builder()
                .success(true)
                .message("Payments retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','CUSTOMER')")
    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        PaymentResponse response =
                paymentService.getPaymentById(id);

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment retrieved successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}")
    public ApiResponse<PaymentResponse> updatePayment(
            @PathVariable Long id,
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.updatePayment(id, request);

        return ApiResponse.<PaymentResponse>builder()
                .success(true)
                .message("Payment updated successfully.")
                .data(response)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Payment deleted successfully.")
                .build();
    }

}
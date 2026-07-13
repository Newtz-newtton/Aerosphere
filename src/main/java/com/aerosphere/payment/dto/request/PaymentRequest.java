package com.aerosphere.payment.dto.request;

import com.aerosphere.payment.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Purpose:
 * Represents a payment creation request.
 *
 * Responsibilities:
 * - Transfer payment creation data.
 * - Validate client input.
 * - Hide entity implementation.
 *
 * Module:
 * Payment
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull(message = "Booking is required.")
    private Long bookingId;

    @NotNull(message = "Payment method is required.")
    private PaymentMethod paymentMethod;

}
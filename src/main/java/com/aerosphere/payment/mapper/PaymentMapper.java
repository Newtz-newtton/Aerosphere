package com.aerosphere.payment.mapper;

import com.aerosphere.payment.dto.response.PaymentResponse;
import com.aerosphere.payment.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Payment entities into PaymentResponse DTOs.
 *
 * Responsibilities:
 * - Transform Payment entity to response DTO.
 * - Expose business-friendly payment information.
 * - Hide internal entity implementation.
 *
 * Module:
 * Payment
 */
@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentReference(payment.getPaymentReference())
                .bookingId(payment.getBooking().getId())
                .bookingReference(
                        payment.getBooking().getBookingReference()
                )
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .build();
    }

}
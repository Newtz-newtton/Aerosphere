package com.aerosphere.payment.dto.response;

import com.aerosphere.payment.entity.PaymentMethod;
import com.aerosphere.payment.entity.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents payment information returned to the client.
 *
 * Responsibilities:
 * - Transfer payment details.
 * - Hide internal entity implementation.
 * - Present business-friendly payment information.
 *
 * Module:
 * Payment
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;

    private String paymentReference;

    private Long bookingId;

    private String bookingReference;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private BigDecimal amount;

    private LocalDateTime paymentDate;

}
package com.aerosphere.kafka.event.payment;

import com.aerosphere.kafka.event.BaseEvent;
import com.aerosphere.payment.entity.PaymentMethod;
import com.aerosphere.payment.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a successfully completed payment event.
 *
 * Responsibilities:
 * - Carry payment completion details.
 * - Trigger downstream business processes.
 * - Provide payment information to Kafka consumers.
 *
 * Module:
 * Kafka Event Platform
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PaymentCompletedEvent extends BaseEvent {

    private Long paymentId;

    private String paymentReference;

    private Long bookingId;

    private String bookingReference;

    private String recipient;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private BigDecimal amount;

    private LocalDateTime paymentDate;

}
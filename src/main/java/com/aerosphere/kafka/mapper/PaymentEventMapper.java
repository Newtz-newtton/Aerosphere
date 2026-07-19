package com.aerosphere.kafka.mapper;

import com.aerosphere.kafka.event.payment.PaymentCompletedEvent;
import com.aerosphere.kafka.util.EventMetadataUtil;
import com.aerosphere.payment.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Maps Payment entities to Kafka payment events.
 *
 * Responsibilities:
 * - Convert Payment entities into payment events.
 * - Populate business event payloads.
 * - Populate common event metadata.
 *
 * Module:
 * Kafka Event Platform
 */
@Component
public class PaymentEventMapper {

    private static final String SOURCE = "payment-service";

    public PaymentCompletedEvent toPaymentCompletedEvent(Payment payment) {

        PaymentCompletedEvent event = PaymentCompletedEvent.builder()
                .paymentId(payment.getId())
                .paymentReference(payment.getPaymentReference())
                .bookingId(payment.getBooking().getId())
                .bookingReference(
                        payment.getBooking().getBookingReference()
                )
                .recipient(
                        payment.getBooking()
                                .getUser()
                                .getEmail()
                )
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .amount(payment.getAmount())
                .paymentDate(payment.getPaymentDate())
                .build();

        EventMetadataUtil.populate(event, SOURCE);

        return event;
    }

}
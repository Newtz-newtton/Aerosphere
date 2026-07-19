package com.aerosphere.kafka.consumer;

import com.aerosphere.kafka.constant.KafkaConstants;
import com.aerosphere.kafka.event.payment.PaymentCompletedEvent;
import com.aerosphere.notification.dto.request.NotificationRequest;
import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.notification.entity.ProviderType;
import com.aerosphere.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Consumes payment completion events and triggers notifications.
 *
 * Responsibilities:
 * - Consume PaymentCompletedEvent messages.
 * - Build notification requests.
 * - Delegate notification processing to NotificationService.
 *
 * Module:
 * Kafka Event Platform
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentCompletedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = KafkaConstants.PAYMENT_EVENTS_TOPIC,
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(PaymentCompletedEvent event) {

        log.info(
                "Received PaymentCompletedEvent for payment reference [{}]",
                event.getPaymentReference()
        );

        NotificationRequest request = NotificationRequest.builder()
                .recipient(event.getRecipient())
                .subject("Payment Successful")
                .message(buildMessage(event))
                .notificationType(NotificationType.PAYMENT)
                .providerType(ProviderType.LOGGING)
                .build();

        notificationService.createNotification(request);

        log.info(
                "Notification triggered for payment reference [{}]",
                event.getPaymentReference()
        );
    }

    private String buildMessage(PaymentCompletedEvent event) {

        return """
                Dear Customer,

                Your payment has been processed successfully.

                Booking Reference : %s
                Payment Reference : %s
                Amount            : %s
                Payment Method    : %s

                Thank you for choosing AeroSphere.
                """.formatted(
                event.getBookingReference(),
                event.getPaymentReference(),
                event.getAmount(),
                event.getPaymentMethod()
        );
    }
}
package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.Notification;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.ProviderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Simulates notification delivery by logging
 * notification details.
 *
 * Responsibilities:
 * - Log notification details.
 * - Mark notification as sent.
 * - Populate provider metadata.
 *
 * Module:
 * Notification
 */
@Slf4j
@Component
public class LoggingNotificationProvider
        implements NotificationProvider {

    @Override
    public Notification send(Notification notification) {

        log.info("""
                
                ==========================================
                AEROSPHERE NOTIFICATION
                ==========================================
                Recipient : {}
                Subject   : {}
                Message   : {}
                Type      : {}
                ==========================================
                """,
                notification.getRecipient(),
                notification.getSubject(),
                notification.getMessage(),
                notification.getNotificationType());

        notification.setProviderType(ProviderType.LOGGING);
        notification.setNotificationStatus(NotificationStatus.SENT);
        notification.setSentAt(LocalDateTime.now());
        notification.setErrorMessage(null);

        return notification;
    }

}
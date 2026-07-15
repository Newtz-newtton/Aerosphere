package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.Notification;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Placeholder for future SMS notification delivery.
 *
 * Module:
 * Notification
 */
@Component
public class SmsNotificationProvider
        implements NotificationProvider {

    @Override
    public Notification send(Notification notification) {

        throw new UnsupportedOperationException(
                "SMS provider will be implemented in a future phase.");

    }

}
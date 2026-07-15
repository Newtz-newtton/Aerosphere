package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.Notification;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Placeholder for future email notification delivery.
 *
 * Module:
 * Notification
 */
@Component
public class EmailNotificationProvider
        implements NotificationProvider {

    @Override
    public Notification send(Notification notification) {

        throw new UnsupportedOperationException(
                "Email provider will be implemented in a future phase.");

    }

}
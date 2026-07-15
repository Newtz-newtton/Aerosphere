package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.Notification;

/**
 * Purpose:
 * Defines the contract for notification delivery providers.
 *
 * Responsibilities:
 * - Deliver notifications.
 * - Update delivery metadata.
 * - Return the updated Notification entity.
 *
 * Module:
 * Notification
 */
public interface NotificationProvider {

    /**
     * Sends the notification using the provider implementation.
     *
     * @param notification Notification to send.
     * @return Updated notification with delivery metadata.
     */
    Notification send(Notification notification);

}
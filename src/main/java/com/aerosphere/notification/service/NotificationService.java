package com.aerosphere.notification.service;

import com.aerosphere.notification.dto.request.NotificationRequest;
import com.aerosphere.notification.dto.response.NotificationResponse;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.NotificationType;

import java.util.List;

/**
 * Purpose:
 * Defines business operations for notification management.
 *
 * Responsibilities:
 * - Create notifications.
 * - Retrieve notification history.
 * - Filter notifications.
 *
 * Module:
 * Notification
 */
public interface NotificationService {

    NotificationResponse createNotification(
            NotificationRequest request);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(
            Long id);

    List<NotificationResponse> getNotificationsByStatus(
            NotificationStatus notificationStatus);

    List<NotificationResponse> getNotificationsByType(
            NotificationType notificationType);

}
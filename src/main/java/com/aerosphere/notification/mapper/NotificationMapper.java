package com.aerosphere.notification.mapper;

import com.aerosphere.notification.dto.response.NotificationResponse;
import com.aerosphere.notification.entity.Notification;
import org.springframework.stereotype.Component;

/**
 * Purpose:
 * Converts Notification entities into response DTOs.
 *
 * Responsibilities:
 * - Map Notification to NotificationResponse.
 *
 * Module:
 * Notification
 */
@Component
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .notificationType(notification.getNotificationType())
                .providerType(notification.getProviderType())
                .notificationStatus(notification.getNotificationStatus())
                .sentAt(notification.getSentAt())
                .errorMessage(notification.getErrorMessage())
                .build();
    }

}
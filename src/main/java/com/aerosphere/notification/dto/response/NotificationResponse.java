package com.aerosphere.notification.dto.response;

import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.notification.entity.ProviderType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a notification response returned to clients.
 *
 * Responsibilities:
 * - Return notification details.
 * - Return provider information.
 * - Return delivery status.
 *
 * Module:
 * Notification
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;

    private String recipient;

    private String subject;

    private String message;

    private NotificationType notificationType;

    private ProviderType providerType;

    private NotificationStatus notificationStatus;

    private LocalDateTime sentAt;

    private String errorMessage;

}
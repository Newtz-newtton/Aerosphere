package com.aerosphere.notification.dto.request;

import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.notification.entity.ProviderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Purpose:
 * Represents a notification creation request.
 *
 * Module:
 * Notification
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    @NotBlank(message = "Recipient is required.")
    //@Email(message = "Recipient must be a valid email address.")
    private String recipient;

    @NotBlank(message = "Subject is required.")
    private String subject;

    @NotBlank(message = "Message is required.")
    private String message;

    @NotNull(message = "Notification type is required.")
    private NotificationType notificationType;

    @NotNull(message = "Provider type is required.")
    private ProviderType providerType;
}
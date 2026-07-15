package com.aerosphere.notification.entity;

import com.aerosphere.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Purpose:
 * Represents a notification generated
 * by the AeroSphere platform.
 *
 * Responsibilities:
 * - Store notification history.
 * - Maintain provider information.
 * - Maintain delivery status.
 * - Support future retry mechanisms.
 *
 * Module:
 * Notification
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 150
    )
    private String recipient;

    @Column(
            nullable = false,
            length = 200
    )
    private String subject;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Column
    private LocalDateTime sentAt;

    @Column(
            columnDefinition = "TEXT"
    )
    private String errorMessage;

}
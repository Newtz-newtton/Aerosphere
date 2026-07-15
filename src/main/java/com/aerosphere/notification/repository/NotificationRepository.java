package com.aerosphere.notification.repository;

import com.aerosphere.notification.entity.Notification;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Purpose:
 * Provides database access operations for Notification entities.
 *
 * Responsibilities:
 * - Perform CRUD operations.
 * - Retrieve notification history.
 * - Filter notifications by status.
 * - Filter notifications by notification type.
 *
 * Module:
 * Notification
 */
@Repository
public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByNotificationStatus(
            NotificationStatus notificationStatus);

    List<Notification> findByNotificationType(
            NotificationType notificationType);

}
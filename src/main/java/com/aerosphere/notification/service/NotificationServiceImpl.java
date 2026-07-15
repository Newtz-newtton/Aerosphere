package com.aerosphere.notification.service;

import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.notification.dto.request.NotificationRequest;
import com.aerosphere.notification.dto.response.NotificationResponse;
import com.aerosphere.notification.entity.Notification;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.mapper.NotificationMapper;
import com.aerosphere.notification.provider.NotificationProvider;
import com.aerosphere.notification.provider.NotificationProviderFactory;
import com.aerosphere.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.notification.util.NotificationValidationUtil;

import java.util.List;

/**
 * Purpose:
 * Implements business operations for notification management.
 *
 * Responsibilities:
 * - Create notifications.
 * - Retrieve notification history.
 * - Filter notification history.
 * - Delegate delivery to notification providers.
 *
 * Module:
 * Notification
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationProviderFactory providerFactory;

    @Override
    public NotificationResponse createNotification(
            NotificationRequest request) {
        validateRecipient(request);

        Notification notification = Notification.builder()
                .recipient(request.getRecipient())
                .subject(request.getSubject())
                .message(request.getMessage())
                .notificationType(request.getNotificationType())
                .providerType(request.getProviderType())
                .notificationStatus(NotificationStatus.PENDING)
                .build();

        Notification savedNotification =
                notificationRepository.save(notification);

        NotificationProvider provider =
                providerFactory.getProvider(
                        savedNotification.getProviderType());

        Notification deliveredNotification =
                provider.send(savedNotification);

        Notification updatedNotification =
                notificationRepository.save(
                        deliveredNotification);

        return notificationMapper.toResponse(
                updatedNotification);
    }
    @Override
    public List<NotificationResponse> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    @Override
    public NotificationResponse getNotificationById(
            Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found."));

        return notificationMapper.toResponse(notification);
    }

    @Override
    public List<NotificationResponse> getNotificationsByStatus(
            NotificationStatus notificationStatus) {

        return notificationRepository
                .findByNotificationStatus(notificationStatus)
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> getNotificationsByType(
            NotificationType notificationType) {

        return notificationRepository
                .findByNotificationType(notificationType)
                .stream()
                .map(notificationMapper::toResponse)
                .toList();
    }

    private void validateRecipient(NotificationRequest request) {

        switch (request.getProviderType()) {

            case LOGGING -> {
                if (!NotificationValidationUtil.isValidEmail(
                        request.getRecipient())) {

                    throw new BusinessException(
                            "Recipient must be a valid email address.");
                }
            }

            case EMAIL -> {

                if (!NotificationValidationUtil.isValidEmail(
                        request.getRecipient())) {

                    throw new BusinessException(
                            "Recipient must be a valid email address.");
                }
            }

            case SMS -> {

                if (!NotificationValidationUtil.isValidPhoneNumber(
                        request.getRecipient())) {

                    throw new BusinessException(
                            "Recipient must be a valid phone number.");
                }
            }
        }
    }
}


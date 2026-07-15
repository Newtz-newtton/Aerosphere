package com.aerosphere.notification.service;

import com.aerosphere.exception.custom.BusinessException;
import com.aerosphere.exception.custom.ResourceNotFoundException;
import com.aerosphere.notification.dto.request.NotificationRequest;
import com.aerosphere.notification.dto.response.NotificationResponse;
import com.aerosphere.notification.entity.Notification;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.notification.entity.ProviderType;
import com.aerosphere.notification.mapper.NotificationMapper;
import com.aerosphere.notification.provider.NotificationProvider;
import com.aerosphere.notification.provider.NotificationProviderFactory;
import com.aerosphere.notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private NotificationProviderFactory providerFactory;

    @Mock
    private NotificationProvider notificationProvider;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    private Notification notification;

    private NotificationRequest request;

    private NotificationResponse response;

    @BeforeEach
    void setUp() {

        request = NotificationRequest.builder()
                .recipient("test@gmail.com")
                .subject("Booking Confirmed")
                .message("Booking successful")
                .notificationType(NotificationType.BOOKING)
                .providerType(ProviderType.LOGGING)
                .build();

        notification = Notification.builder()
                .id(1L)
                .recipient(request.getRecipient())
                .subject(request.getSubject())
                .message(request.getMessage())
                .notificationType(request.getNotificationType())
                .providerType(request.getProviderType())
                .notificationStatus(NotificationStatus.SENT)
                .sentAt(LocalDateTime.now())
                .build();

        response = NotificationResponse.builder()
                .id(1L)
                .recipient(notification.getRecipient())
                .subject(notification.getSubject())
                .message(notification.getMessage())
                .notificationType(notification.getNotificationType())
                .providerType(notification.getProviderType())
                .notificationStatus(notification.getNotificationStatus())
                .sentAt(notification.getSentAt())
                .build();
    }

    @Test
    void createNotification_ShouldReturnNotificationResponse() {

        when(notificationRepository.save(any(Notification.class)))
                .thenReturn(notification);

        when(providerFactory.getProvider(ProviderType.LOGGING))
                .thenReturn(notificationProvider);

        when(notificationProvider.send(notification))
                .thenReturn(notification);

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        NotificationResponse result =
                notificationService.createNotification(request);

        assertNotNull(result);

        assertEquals(
                response.getRecipient(),
                result.getRecipient());

        assertEquals(
                NotificationStatus.SENT,
                result.getNotificationStatus());

        verify(notificationRepository, times(2))
                .save(any(Notification.class));

        verify(providerFactory)
                .getProvider(ProviderType.LOGGING);

        verify(notificationProvider)
                .send(notification);

        verify(notificationMapper)
                .toResponse(notification);
    }

    @Test
    void createNotification_ShouldThrowException_WhenEmailIsInvalid() {

        request.setProviderType(ProviderType.EMAIL);
        request.setRecipient("invalid-email");

        assertThrows(
                BusinessException.class,
                () -> notificationService.createNotification(request));

        verify(notificationRepository, never())
                .save(any(Notification.class));

        verifyNoInteractions(providerFactory);
    }

    @Test
    void createNotification_ShouldThrowException_WhenPhoneNumberIsInvalid() {

        request.setProviderType(ProviderType.SMS);
        request.setRecipient("abc123");

        assertThrows(
                BusinessException.class,
                () -> notificationService.createNotification(request));

        verify(notificationRepository, never())
                .save(any(Notification.class));

        verifyNoInteractions(providerFactory);
    }

    @Test
    void createNotification_ShouldUseLoggingProvider() {

        when(notificationRepository.save(any(Notification.class)))
                .thenReturn(notification);

        when(providerFactory.getProvider(ProviderType.LOGGING))
                .thenReturn(notificationProvider);

        when(notificationProvider.send(notification))
                .thenReturn(notification);

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        notificationService.createNotification(request);

        verify(providerFactory)
                .getProvider(ProviderType.LOGGING);

        verify(notificationProvider)
                .send(notification);
    }

    @Test
    void createNotification_ShouldSetProviderMetadata() {

        notification.setProviderType(ProviderType.LOGGING);
        notification.setNotificationStatus(NotificationStatus.SENT);

        when(notificationRepository.save(any(Notification.class)))
                .thenReturn(notification);

        when(providerFactory.getProvider(ProviderType.LOGGING))
                .thenReturn(notificationProvider);

        when(notificationProvider.send(notification))
                .thenReturn(notification);

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        NotificationResponse result =
                notificationService.createNotification(request);

        assertEquals(
                ProviderType.LOGGING,
                result.getProviderType());

        assertEquals(
                NotificationStatus.SENT,
                result.getNotificationStatus());

        assertNotNull(result.getSentAt());
    }

    @Test
    void getAllNotifications_ShouldReturnNotificationList() {

        when(notificationRepository.findAll())
                .thenReturn(List.of(notification));

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        List<NotificationResponse> result =
                notificationService.getAllNotifications();

        assertEquals(1, result.size());

        verify(notificationRepository).findAll();

        verify(notificationMapper)
                .toResponse(notification);
    }

    @Test
    void getNotificationById_ShouldReturnNotification() {

        when(notificationRepository.findById(1L))
                .thenReturn(Optional.of(notification));

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        NotificationResponse result =
                notificationService.getNotificationById(1L);

        assertNotNull(result);

        assertEquals(
                response.getId(),
                result.getId());

        verify(notificationRepository)
                .findById(1L);
    }

    @Test
    void getNotificationById_ShouldThrowException_WhenNotificationNotFound() {

        when(notificationRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> notificationService.getNotificationById(1L));

        verify(notificationRepository)
                .findById(1L);
    }

    @Test
    void getNotificationsByStatus_ShouldReturnMatchingNotifications() {

        when(notificationRepository.findByNotificationStatus(
                NotificationStatus.SENT))
                .thenReturn(List.of(notification));

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        List<NotificationResponse> result =
                notificationService.getNotificationsByStatus(
                        NotificationStatus.SENT);

        assertEquals(1, result.size());

        verify(notificationRepository)
                .findByNotificationStatus(NotificationStatus.SENT);
    }

    @Test
    void getNotificationsByType_ShouldReturnMatchingNotifications() {

        when(notificationRepository.findByNotificationType(
                NotificationType.BOOKING))
                .thenReturn(List.of(notification));

        when(notificationMapper.toResponse(notification))
                .thenReturn(response);

        List<NotificationResponse> result =
                notificationService.getNotificationsByType(
                        NotificationType.BOOKING);

        assertEquals(1, result.size());

        verify(notificationRepository)
                .findByNotificationType(NotificationType.BOOKING);
    }
}





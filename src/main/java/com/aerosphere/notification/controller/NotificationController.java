package com.aerosphere.notification.controller;

import com.aerosphere.common.dto.ApiResponse;
import com.aerosphere.notification.dto.request.NotificationRequest;
import com.aerosphere.notification.dto.response.NotificationResponse;
import com.aerosphere.notification.entity.NotificationStatus;
import com.aerosphere.notification.entity.NotificationType;
import com.aerosphere.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Purpose:
 * Exposes REST APIs for notification management.
 *
 * Responsibilities:
 * - Create notifications.
 * - Retrieve notification history.
 * - Filter notifications.
 *
 * Module:
 * Notification
 */
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ApiResponse<NotificationResponse> createNotification(
            @Valid @RequestBody NotificationRequest request) {

        NotificationResponse response =
                notificationService.createNotification(request);

        return ApiResponse.<NotificationResponse>builder()
                .success(true)
                .message("Notification created successfully.")
                .data(response)
                .build();
    }

    @GetMapping
    public ApiResponse<List<NotificationResponse>> getAllNotifications() {

        List<NotificationResponse> response =
                notificationService.getAllNotifications();

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Notifications retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<NotificationResponse> getNotificationById(
            @PathVariable Long id) {

        NotificationResponse response =
                notificationService.getNotificationById(id);

        return ApiResponse.<NotificationResponse>builder()
                .success(true)
                .message("Notification retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<NotificationResponse>>
    getNotificationsByStatus(
            @PathVariable NotificationStatus status) {

        List<NotificationResponse> response =
                notificationService.getNotificationsByStatus(status);

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Notifications retrieved successfully.")
                .data(response)
                .build();
    }

    @GetMapping("/type/{type}")
    public ApiResponse<List<NotificationResponse>>
    getNotificationsByType(
            @PathVariable NotificationType type) {

        List<NotificationResponse> response =
                notificationService.getNotificationsByType(type);

        return ApiResponse.<List<NotificationResponse>>builder()
                .success(true)
                .message("Notifications retrieved successfully.")
                .data(response)
                .build();
    }

}
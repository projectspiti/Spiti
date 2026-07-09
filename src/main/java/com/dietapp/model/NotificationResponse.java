package com.dietapp.model;

public record NotificationResponse(
        Long userId,
        NotificationType type,
        NotificationChannelType channel,
        String message,
        String deliveryId,
        String status
) {
}

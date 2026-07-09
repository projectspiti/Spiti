package com.dietapp.model;

public record NotificationRequest(
        Long userId,
        NotificationType type,
        NotificationChannelType channel,
        String messageData
) {
}

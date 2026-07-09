package com.dietapp.service.notification;

import com.dietapp.model.NotificationResponse;
import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.channel.NotificationChannel;

public abstract class Notification {

    private final NotificationChannel channel;

    protected Notification(NotificationChannel channel) {
        this.channel = channel;
    }

    public NotificationResponse send(Long userId, String messageData) {
        String message = buildMessage(messageData);
        String deliveryId = channel.send(userId, message);
        return new NotificationResponse(
                userId,
                type(),
                channel.channelType(),
                message,
                deliveryId,
                "SENT"
        );
    }

    protected abstract NotificationType type();

    protected abstract String buildMessage(String messageData);
}

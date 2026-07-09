package com.dietapp.service.notification;

import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.channel.NotificationChannel;

public class StreakNotification extends Notification {

    public StreakNotification(NotificationChannel channel) {
        super(channel);
    }

    @Override
    protected NotificationType type() {
        return NotificationType.STREAK;
    }

    @Override
    protected String buildMessage(String messageData) {
        return "Streak update: " + messageData;
    }
}

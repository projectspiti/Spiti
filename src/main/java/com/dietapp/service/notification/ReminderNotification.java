package com.dietapp.service.notification;

import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.channel.NotificationChannel;

public class ReminderNotification extends Notification {

    public ReminderNotification(NotificationChannel channel) {
        super(channel);
    }

    @Override
    protected NotificationType type() {
        return NotificationType.REMINDER;
    }

    @Override
    protected String buildMessage(String messageData) {
        return "Reminder: " + messageData;
    }
}

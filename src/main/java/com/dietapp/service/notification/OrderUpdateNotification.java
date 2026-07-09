package com.dietapp.service.notification;

import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.channel.NotificationChannel;

public class OrderUpdateNotification extends Notification {

    public OrderUpdateNotification(NotificationChannel channel) {
        super(channel);
    }

    @Override
    protected NotificationType type() {
        return NotificationType.ORDER_UPDATE;
    }

    @Override
    protected String buildMessage(String messageData) {
        return "Order update: " + messageData;
    }
}

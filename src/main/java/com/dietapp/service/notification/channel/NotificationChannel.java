package com.dietapp.service.notification.channel;

import com.dietapp.model.NotificationChannelType;

public interface NotificationChannel {

    NotificationChannelType channelType();

    String send(Long userId, String message);
}

package com.dietapp.service.notification.channel;

import com.dietapp.model.NotificationChannelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PushNotificationChannel implements NotificationChannel {

    private static final Logger log = LoggerFactory.getLogger(PushNotificationChannel.class);

    @Override
    public NotificationChannelType channelType() {
        return NotificationChannelType.PUSH;
    }

    @Override
    public String send(Long userId, String message) {
        String deliveryId = "push-" + userId + "-" + Math.abs(message.hashCode());
        log.info("Push notification sent userId={}, deliveryId={}", userId, deliveryId);
        return deliveryId;
    }
}

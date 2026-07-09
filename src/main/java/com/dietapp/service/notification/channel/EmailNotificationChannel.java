package com.dietapp.service.notification.channel;

import com.dietapp.model.NotificationChannelType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationChannel implements NotificationChannel {

    private static final Logger log = LoggerFactory.getLogger(EmailNotificationChannel.class);

    @Override
    public NotificationChannelType channelType() {
        return NotificationChannelType.EMAIL;
    }

    @Override
    public String send(Long userId, String message) {
        String deliveryId = "email-" + userId + "-" + Math.abs(message.hashCode());
        log.info("Email notification sent userId={}, deliveryId={}", userId, deliveryId);
        return deliveryId;
    }
}

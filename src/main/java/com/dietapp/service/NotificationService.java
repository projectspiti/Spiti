package com.dietapp.service;

import com.dietapp.model.NotificationChannelType;
import com.dietapp.model.NotificationRequest;
import com.dietapp.model.NotificationResponse;
import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.Notification;
import com.dietapp.service.notification.OrderUpdateNotification;
import com.dietapp.service.notification.ReminderNotification;
import com.dietapp.service.notification.StreakNotification;
import com.dietapp.service.notification.channel.NotificationChannel;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final Map<NotificationChannelType, NotificationChannel> channelsByType;

    public NotificationService(List<NotificationChannel> channels) {
        this.channelsByType = new EnumMap<>(NotificationChannelType.class);
        for (NotificationChannel channel : channels) {
            NotificationChannel existingChannel = channelsByType.put(channel.channelType(), channel);
            if (existingChannel != null) {
                throw new IllegalStateException("Duplicate notification channel: " + channel.channelType());
            }
        }
        log.info("Loaded {} notification channels", channelsByType.size());
    }

    public NotificationResponse send(NotificationRequest request) {
        validate(request);
        NotificationChannel channel = resolveChannel(request.channel());
        Notification notification = createNotification(request.type(), channel);

        log.info("Sending notification userId={}, type={}, channel={}",
                request.userId(), request.type(), request.channel());
        NotificationResponse response = notification.send(request.userId(), request.messageData());
        log.info("Notification delivery completed userId={}, type={}, channel={}, deliveryId={}",
                response.userId(), response.type(), response.channel(), response.deliveryId());
        return response;
    }

    private NotificationChannel resolveChannel(NotificationChannelType channelType) {
        NotificationChannel channel = channelsByType.get(channelType);
        if (channel == null) {
            throw new IllegalArgumentException("Unsupported notification channel: " + channelType);
        }
        return channel;
    }

    private Notification createNotification(NotificationType type, NotificationChannel channel) {
        return switch (type) {
            case REMINDER -> new ReminderNotification(channel);
            case ORDER_UPDATE -> new OrderUpdateNotification(channel);
            case STREAK -> new StreakNotification(channel);
        };
    }

    private void validate(NotificationRequest request) {
        if (request.userId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (request.type() == null) {
            throw new IllegalArgumentException("type is required");
        }
        if (request.channel() == null) {
            throw new IllegalArgumentException("channel is required");
        }
        if (request.messageData() == null || request.messageData().isBlank()) {
            throw new IllegalArgumentException("messageData is required");
        }
    }
}

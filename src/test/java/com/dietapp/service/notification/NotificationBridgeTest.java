package com.dietapp.service.notification;

import com.dietapp.model.NotificationChannelType;
import com.dietapp.model.NotificationResponse;
import com.dietapp.model.NotificationType;
import com.dietapp.service.notification.channel.NotificationChannel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NotificationBridgeTest {

    @Test
    void notificationTypeAndChannelCanVaryIndependently() {
        RecordingChannel smsChannel = new RecordingChannel(NotificationChannelType.SMS);
        RecordingChannel emailChannel = new RecordingChannel(NotificationChannelType.EMAIL);

        Notification reminderSms = new ReminderNotification(smsChannel);
        Notification orderEmail = new OrderUpdateNotification(emailChannel);

        NotificationResponse reminderResponse = reminderSms.send(101L, "Log dinner before 9 PM");
        NotificationResponse orderResponse = orderEmail.send(101L, "Lunch order is out for delivery");

        assertThat(reminderResponse.type()).isEqualTo(NotificationType.REMINDER);
        assertThat(reminderResponse.channel()).isEqualTo(NotificationChannelType.SMS);
        assertThat(smsChannel.lastMessage).isEqualTo("Reminder: Log dinner before 9 PM");

        assertThat(orderResponse.type()).isEqualTo(NotificationType.ORDER_UPDATE);
        assertThat(orderResponse.channel()).isEqualTo(NotificationChannelType.EMAIL);
        assertThat(emailChannel.lastMessage).isEqualTo("Order update: Lunch order is out for delivery");
    }

    private static class RecordingChannel implements NotificationChannel {
        private final NotificationChannelType channelType;
        private String lastMessage;

        private RecordingChannel(NotificationChannelType channelType) {
            this.channelType = channelType;
        }

        @Override
        public NotificationChannelType channelType() {
            return channelType;
        }

        @Override
        public String send(Long userId, String message) {
            lastMessage = message;
            return channelType.name().toLowerCase() + "-" + userId;
        }
    }
}

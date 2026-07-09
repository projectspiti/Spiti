package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserPushNotificationObserver implements OrderStatusObserver {

    private static final Logger log = LoggerFactory.getLogger(UserPushNotificationObserver.class);

    @Override
    public String name() {
        return "UserPushNotification";
    }

    @Override
    public OrderObserverNotification onStatusChanged(OrderStatusChangedEvent event) {
        String message = "User notified that order " + event.orderId()
                + " is now " + event.currentStatus();
        log.info("User push notification queued orderId={}, currentStatus={}",
                event.orderId(), event.currentStatus());
        return new OrderObserverNotification(name(), message);
    }
}

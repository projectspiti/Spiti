package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KitchenDashboardObserver implements OrderStatusObserver {

    private static final Logger log = LoggerFactory.getLogger(KitchenDashboardObserver.class);

    @Override
    public String name() {
        return "KitchenDashboard";
    }

    @Override
    public OrderObserverNotification onStatusChanged(OrderStatusChangedEvent event) {
        String message = "Kitchen dashboard updated for order " + event.orderId()
                + " with status " + event.currentStatus();
        log.info("Kitchen dashboard updated orderId={}, currentStatus={}",
                event.orderId(), event.currentStatus());
        return new OrderObserverNotification(name(), message);
    }
}

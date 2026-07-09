package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatus;
import com.dietapp.model.OrderStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeliveryPartnerObserver implements OrderStatusObserver {

    private static final Logger log = LoggerFactory.getLogger(DeliveryPartnerObserver.class);

    @Override
    public String name() {
        return "DeliveryPartnerService";
    }

    @Override
    public OrderObserverNotification onStatusChanged(OrderStatusChangedEvent event) {
        String message = event.currentStatus() == OrderStatus.OUT_FOR_DELIVERY
                ? "Delivery partner assigned for order " + event.orderId()
                : "Delivery partner service observed order " + event.orderId()
                + " status " + event.currentStatus();
        log.info("Delivery partner service notified orderId={}, currentStatus={}",
                event.orderId(), event.currentStatus());
        return new OrderObserverNotification(name(), message);
    }
}

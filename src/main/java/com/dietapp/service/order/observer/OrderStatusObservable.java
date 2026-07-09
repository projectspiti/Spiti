package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatusChangedEvent;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusObservable {

    private static final Logger log = LoggerFactory.getLogger(OrderStatusObservable.class);

    private final List<OrderStatusObserver> observers = new ArrayList<>();

    public OrderStatusObservable(List<OrderStatusObserver> observers) {
        this.observers.addAll(observers);
        log.info("Registered {} order status observers", observers.size());
    }

    public void register(OrderStatusObserver observer) {
        observers.add(observer);
    }

    public List<OrderObserverNotification> notifyObservers(OrderStatusChangedEvent event) {
        log.info("Notifying order status observers orderId={}, previousStatus={}, currentStatus={}",
                event.orderId(), event.previousStatus(), event.currentStatus());
        return observers.stream()
                .map(observer -> observer.onStatusChanged(event))
                .toList();
    }
}

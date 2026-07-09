package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatusChangedEvent;

public interface OrderStatusObserver {

    String name();

    OrderObserverNotification onStatusChanged(OrderStatusChangedEvent event);
}

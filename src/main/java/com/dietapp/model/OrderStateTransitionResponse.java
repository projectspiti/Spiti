package com.dietapp.model;

import java.util.List;

public record OrderStateTransitionResponse(
        String orderId,
        OrderStatus previousStatus,
        OrderStatus currentStatus,
        String message,
        List<OrderObserverNotification> observerNotifications
) {
}

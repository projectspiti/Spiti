package com.dietapp.model;

import java.time.Instant;

public record OrderStatusChangedEvent(
        String orderId,
        OrderStatus previousStatus,
        OrderStatus currentStatus,
        Instant changedAt
) {
}

package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;

public interface OrderState {

    OrderStatus status();

    default OrderStatus startPreparing() {
        throw invalid("start preparing");
    }

    default OrderStatus markOutForDelivery() {
        throw invalid("mark out for delivery");
    }

    default OrderStatus markDelivered() {
        throw invalid("mark delivered");
    }

    private IllegalStateException invalid(String action) {
        return new IllegalStateException("Cannot " + action + " from " + status() + " state");
    }
}

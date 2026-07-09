package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;

public class OutForDeliveryOrderState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.OUT_FOR_DELIVERY;
    }

    @Override
    public OrderStatus markDelivered() {
        return OrderStatus.DELIVERED;
    }
}

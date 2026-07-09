package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;

public class DeliveredOrderState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.DELIVERED;
    }
}

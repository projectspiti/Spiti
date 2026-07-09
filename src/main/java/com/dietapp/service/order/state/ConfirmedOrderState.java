package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;

public class ConfirmedOrderState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.CONFIRMED;
    }

    @Override
    public OrderStatus startPreparing() {
        return OrderStatus.PREPARING;
    }
}

package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;

public class PreparingOrderState implements OrderState {

    @Override
    public OrderStatus status() {
        return OrderStatus.PREPARING;
    }

    @Override
    public OrderStatus markOutForDelivery() {
        return OrderStatus.OUT_FOR_DELIVERY;
    }
}

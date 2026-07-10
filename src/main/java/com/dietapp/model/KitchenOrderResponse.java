package com.dietapp.model;

import java.util.List;

public record KitchenOrderResponse(
        String orderId,
        String station,
        String packaging,
        String deliverySlot,
        List<String> coordinationSteps
) {
}

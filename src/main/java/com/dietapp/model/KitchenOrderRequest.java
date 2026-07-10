package com.dietapp.model;

public record KitchenOrderRequest(
        String orderId,
        String mealName,
        DayType dayType,
        boolean priorityOrder
) {
}

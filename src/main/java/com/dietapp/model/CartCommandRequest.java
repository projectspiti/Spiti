package com.dietapp.model;

public record CartCommandRequest(
        String cartId,
        CartCommandType commandType,
        String idempotencyKey,
        String mealName,
        String replacementMealName,
        String couponCode
) {
}

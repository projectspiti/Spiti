package com.dietapp.model;

import java.util.List;

public record CartCommandResponse(
        String cartId,
        CartCommandType commandType,
        String idempotencyKey,
        String status,
        List<String> meals,
        String couponCode,
        String message
) {
}

package com.dietapp.model;

import java.util.List;

public record CartUndoResponse(
        String cartId,
        String undoneCommand,
        List<String> meals,
        String couponCode,
        String message
) {
}

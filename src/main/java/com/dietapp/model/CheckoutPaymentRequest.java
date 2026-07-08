package com.dietapp.model;

public record CheckoutPaymentRequest(
        String orderId,
        Long userId,
        int amountInRupees,
        PaymentMethod paymentMethod
) {
}

package com.dietapp.model;

public record CheckoutPaymentResponse(
        String orderId,
        String providerOrderId,
        int amountInRupees,
        PaymentStatus status,
        String paymentLink,
        String providerName,
        String message
) {
}

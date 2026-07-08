package com.dietapp.service.payment;

public record RazorpayOrder(
        String providerOrderId,
        int amountInPaise,
        String currency,
        String receiptId,
        String paymentLink,
        String providerStatus
) {
}

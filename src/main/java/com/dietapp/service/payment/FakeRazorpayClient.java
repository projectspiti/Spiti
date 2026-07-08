package com.dietapp.service.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FakeRazorpayClient {
    private static final Logger log = LoggerFactory.getLogger(FakeRazorpayClient.class);

    public RazorpayOrder createOrder(int amountInPaise, String currency, String receiptId) {
        log.info("Adapter Pattern flow: FakeRazorpayClient.createOrder() called with amountInPaise={}, currency={}, receiptId={}",
                amountInPaise, currency, receiptId);

        String providerOrderId = "rzp_order_" + receiptId;
        String paymentLink = "https://payments.example.test/razorpay/" + providerOrderId;

        log.info("Adapter Pattern flow: FakeRazorpayClient returned providerOrderId={}", providerOrderId);
        return new RazorpayOrder(providerOrderId, amountInPaise, currency, receiptId, paymentLink, "created");
    }
}

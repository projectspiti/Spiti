package com.dietapp.service;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.CheckoutPaymentResponse;
import com.dietapp.service.payment.PaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {
    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    private final PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public CheckoutPaymentResponse createPayment(CheckoutPaymentRequest request) {
        validate(request);
        log.info("Creating payment order orderId={}, userId={}, amountInRupees={}, paymentMethod={}",
                request.orderId(), request.userId(), request.amountInRupees(), request.paymentMethod());
        CheckoutPaymentResponse response = paymentGateway.createPayment(request);
        log.info("Payment order created providerOrderId={}",
                response.providerOrderId());
        return response;
    }

    private void validate(CheckoutPaymentRequest request) {
        if (request.orderId() == null || request.orderId().isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.userId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (request.amountInRupees() <= 0) {
            throw new IllegalArgumentException("amountInRupees must be greater than zero");
        }
        if (request.paymentMethod() == null) {
            throw new IllegalArgumentException("paymentMethod is required");
        }
    }
}

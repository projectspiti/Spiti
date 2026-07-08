package com.dietapp.service.payment;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.CheckoutPaymentResponse;

public interface PaymentGateway {
    CheckoutPaymentResponse createPayment(CheckoutPaymentRequest request);
}

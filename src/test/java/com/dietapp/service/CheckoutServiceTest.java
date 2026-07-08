package com.dietapp.service;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.PaymentMethod;
import com.dietapp.service.payment.FakeRazorpayClient;
import com.dietapp.service.payment.RazorpayPaymentAdapter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckoutServiceTest {

    @Test
    void usesPaymentGatewayInterfaceToCreatePayment() {
        CheckoutService service = new CheckoutService(
                new RazorpayPaymentAdapter(new FakeRazorpayClient())
        );

        assertThat(service.createPayment(new CheckoutPaymentRequest("ORDER-101", 501L, 485, PaymentMethod.UPI))
                .providerName()).isEqualTo("RAZORPAY");
    }

    @Test
    void rejectsInvalidAmountBeforeCallingGateway() {
        CheckoutService service = new CheckoutService(
                new RazorpayPaymentAdapter(new FakeRazorpayClient())
        );

        assertThatThrownBy(() -> service.createPayment(
                new CheckoutPaymentRequest("ORDER-101", 501L, 0, PaymentMethod.UPI)
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("amountInRupees must be greater than zero");
    }
}

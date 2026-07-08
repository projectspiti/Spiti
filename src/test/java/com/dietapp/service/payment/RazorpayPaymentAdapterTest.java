package com.dietapp.service.payment;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.CheckoutPaymentResponse;
import com.dietapp.model.PaymentMethod;
import com.dietapp.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RazorpayPaymentAdapterTest {

    @Test
    void convertsAppPaymentRequestToRazorpayRequestAndBack() {
        RazorpayPaymentAdapter adapter = new RazorpayPaymentAdapter(new FakeRazorpayClient());

        CheckoutPaymentResponse response = adapter.createPayment(
                new CheckoutPaymentRequest("ORDER-101", 501L, 485, PaymentMethod.UPI)
        );

        assertThat(response.orderId()).isEqualTo("ORDER-101");
        assertThat(response.providerOrderId()).isEqualTo("rzp_order_ORDER-101");
        assertThat(response.amountInRupees()).isEqualTo(485);
        assertThat(response.status()).isEqualTo(PaymentStatus.CREATED);
        assertThat(response.providerName()).isEqualTo("RAZORPAY");
        assertThat(response.paymentLink()).contains("rzp_order_ORDER-101");
    }
}

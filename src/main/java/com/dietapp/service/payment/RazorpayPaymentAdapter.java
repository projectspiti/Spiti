package com.dietapp.service.payment;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.CheckoutPaymentResponse;
import com.dietapp.model.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentAdapter implements PaymentGateway {
    private static final Logger log = LoggerFactory.getLogger(RazorpayPaymentAdapter.class);

    private final FakeRazorpayClient razorpayClient;

    public RazorpayPaymentAdapter(FakeRazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public CheckoutPaymentResponse createPayment(CheckoutPaymentRequest request) {
        log.info("Adapter Pattern flow: RazorpayPaymentAdapter received app request orderId={}, amountInRupees={}",
                request.orderId(), request.amountInRupees());

        int amountInPaise = request.amountInRupees() * 100;
        String receiptId = request.orderId();
        log.info("Adapter Pattern flow: adapter converting rupees to paise: {} -> {}",
                request.amountInRupees(), amountInPaise);

        RazorpayOrder razorpayOrder = razorpayClient.createOrder(amountInPaise, "INR", receiptId);
        log.info("Adapter Pattern flow: adapter converting RazorpayOrder to CheckoutPaymentResponse");

        return new CheckoutPaymentResponse(
                request.orderId(),
                razorpayOrder.providerOrderId(),
                razorpayOrder.amountInPaise() / 100,
                PaymentStatus.CREATED,
                razorpayOrder.paymentLink(),
                "RAZORPAY",
                "Payment order created through Razorpay adapter"
        );
    }
}

package com.dietapp.controller;

import com.dietapp.model.CheckoutPaymentRequest;
import com.dietapp.model.CheckoutPaymentResponse;
import com.dietapp.service.CheckoutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/pay")
    public CheckoutPaymentResponse pay(@RequestBody CheckoutPaymentRequest request) {
        return checkoutService.createPayment(request);
    }
}

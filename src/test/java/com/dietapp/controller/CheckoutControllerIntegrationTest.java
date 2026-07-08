package com.dietapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsPaymentThroughAdapterFlow() throws Exception {
        mockMvc.perform(post("/api/checkout/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-101",
                                  "userId": 501,
                                  "amountInRupees": 485,
                                  "paymentMethod": "UPI"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("ORDER-101"))
                .andExpect(jsonPath("$.providerOrderId").value("rzp_order_ORDER-101"))
                .andExpect(jsonPath("$.amountInRupees").value(485))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.providerName").value("RAZORPAY"))
                .andExpect(jsonPath("$.paymentLink").value("https://payments.example.test/razorpay/rzp_order_ORDER-101"));
    }
}

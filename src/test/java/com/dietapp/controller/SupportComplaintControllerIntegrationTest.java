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
class SupportComplaintControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void resolvesPaymentComplaintUsingPaymentHandler() throws Exception {
        mockMvc.perform(post("/api/support/complaints/resolve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-201",
                                  "issueType": "PAYMENT_FAILED",
                                  "description": "UPI payment failed but amount debited"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("ORDER-201"))
                .andExpect(jsonPath("$.issueType").value("PAYMENT_FAILED"))
                .andExpect(jsonPath("$.handledBy").value("PaymentSupportHandler"));
    }

    @Test
    void returnsBadRequestWhenIssueTypeIsMissing() throws Exception {
        mockMvc.perform(post("/api/support/complaints/resolve")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-202",
                                  "description": "Need support"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("issueType is required"));
    }
}

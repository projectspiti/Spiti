package com.dietapp.controller;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderStateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void movesOrderThroughValidLifecycle() throws Exception {
        String orderId = "ORDER-" + UUID.randomUUID();

        mockMvc.perform(post("/api/orders/{orderId}/state/confirm", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentStatus").value("CONFIRMED"))
                .andExpect(jsonPath("$.observerNotifications", hasSize(3)));

        mockMvc.perform(post("/api/orders/{orderId}/state/prepare", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previousStatus").value("CONFIRMED"))
                .andExpect(jsonPath("$.currentStatus").value("PREPARING"))
                .andExpect(jsonPath("$.observerNotifications", hasSize(3)));

        mockMvc.perform(post("/api/orders/{orderId}/state/out-for-delivery", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previousStatus").value("PREPARING"))
                .andExpect(jsonPath("$.currentStatus").value("OUT_FOR_DELIVERY"));

        mockMvc.perform(post("/api/orders/{orderId}/state/deliver", orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previousStatus").value("OUT_FOR_DELIVERY"))
                .andExpect(jsonPath("$.currentStatus").value("DELIVERED"));
    }

    @Test
    void rejectsInvalidLifecycleTransition() throws Exception {
        String orderId = "ORDER-" + UUID.randomUUID();

        mockMvc.perform(post("/api/orders/{orderId}/state/confirm", orderId))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/orders/{orderId}/state/deliver", orderId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Cannot mark delivered from CONFIRMED state"));
    }
}

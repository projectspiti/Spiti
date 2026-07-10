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
class KitchenOrderMediatorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void coordinatesKitchenOrderUsingMediator() throws Exception {
        mockMvc.perform(post("/api/kitchen/orders/coordinate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-402",
                                  "mealName": "Paneer Bowl",
                                  "dayType": "OFFICE",
                                  "priorityOrder": false
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("Vegetarian hot station"))
                .andExpect(jsonPath("$.coordinationSteps[0]").value("Ingredients reserved for Paneer Bowl"));
    }
}

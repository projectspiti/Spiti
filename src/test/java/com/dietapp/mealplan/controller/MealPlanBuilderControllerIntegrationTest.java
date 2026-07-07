package com.dietapp.mealplan.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MealPlanBuilderControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void buildsMealPlanFromPostRequest() throws Exception {
        mockMvc.perform(post("/api/meal-plans/builder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": 101,
                                  "targetCalories": 1600,
                                  "nutritionMode": "VEG",
                                  "deliveryRequired": true
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(101))
                .andExpect(jsonPath("$.targetCalories").value(1600))
                .andExpect(jsonPath("$.nutritionMode").value("VEG"))
                .andExpect(jsonPath("$.goal").value("WEIGHT_LOSS"))
                .andExpect(jsonPath("$.meals", contains("Paneer protein bowl", "Low-calorie dal soup")))
                .andExpect(jsonPath("$.deliveryRequired").value(true))
                .andExpect(jsonPath("$.deliverySlot").value("LUNCH_SLOT"));
    }
}

package com.dietapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OnboardingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void completesOnboardingThroughFacade() throws Exception {
        mockMvc.perform(post("/api/onboarding/complete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": 101,
                                  "age": 29,
                                  "weightKg": 82,
                                  "heightCm": 176,
                                  "gender": "MALE",
                                  "activityLevel": "MODERATE",
                                  "goalType": "CUT",
                                  "bodyFatPercentage": null,
                                  "medicalConditions": [],
                                  "nutritionMode": "VEG",
                                  "deliveryRequired": true,
                                  "days": [
                                    {
                                      "date": "2026-07-09",
                                      "dayType": "HOME",
                                      "priceTier": "STANDARD"
                                    },
                                    {
                                      "date": "2026-07-10",
                                      "dayType": "OFFICE",
                                      "priceTier": "PREMIUM"
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(101))
                .andExpect(jsonPath("$.calorieTarget.targetCalories").isNumber())
                .andExpect(jsonPath("$.nutritionPlan.nutritionMode").value("VEG"))
                .andExpect(jsonPath("$.weeklyPlan.days", hasSize(2)))
                .andExpect(jsonPath("$.starterMealPlan.userId").value(101))
                .andExpect(jsonPath("$.starterMealPlan.deliveryRequired").value(true));
    }
}

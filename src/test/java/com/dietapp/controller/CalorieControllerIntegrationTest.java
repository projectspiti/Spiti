package com.dietapp.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CalorieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void calculatesTargetCaloriesThroughRestEndpoint() throws Exception {
        String requestBody = """
                {
                  "age": 28,
                  "weightKg": 80,
                  "heightCm": 175,
                  "gender": "MALE",
                  "activityLevel": "MODERATE",
                  "goalType": "CUT",
                  "bodyFatPercentage": 20,
                  "medicalConditions": []
                }
                """;

        mockMvc.perform(post("/api/calories/target")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bmr").value(1752))
                .andExpect(jsonPath("$.maintenanceCalories").value(2716))
                .andExpect(jsonPath("$.targetCalories").value(2173))
                .andExpect(jsonPath("$.bmrStrategy").value("KATCH_MC_ARDLE"))
                .andExpect(jsonPath("$.goalStrategy").value("CUT_20_PERCENT_DEFICIT"))
                .andExpect(jsonPath("$.explanation", containsString("KATCH_MC_ARDLE")));
    }
}

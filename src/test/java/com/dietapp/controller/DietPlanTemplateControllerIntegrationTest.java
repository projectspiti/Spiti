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
class DietPlanTemplateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsDietPlanFromTemplate() throws Exception {
        mockMvc.perform(post("/api/diet-plan-templates/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": 101,
                                  "goal": "CUT",
                                  "dayType": "OFFICE",
                                  "intermittentFastingEnabled": true,
                                  "fastingWindow": "12 PM - 8 PM"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.goal").value("CUT"))
                .andExpect(jsonPath("$.calorieTarget").value(1800))
                .andExpect(jsonPath("$.executedSteps[0]").value("calcTarget"))
                .andExpect(jsonPath("$.executedSteps[3]").value("applyIFWindow"));
    }
}

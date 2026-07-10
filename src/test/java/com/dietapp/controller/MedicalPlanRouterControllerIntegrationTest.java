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
class MedicalPlanRouterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void routesMedicalPlanThroughHandlers() throws Exception {
        mockMvc.perform(post("/api/medical-plan-router/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "userId": 101,
                                  "conditions": ["DIABETES", "PCOS"],
                                  "intermittentFastingRequested": true
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.intermittentFastingAllowed").value(false))
                .andExpect(jsonPath("$.adjustments[0]").value("Use low glycemic carbs and avoid sugar-heavy snacks"));
    }
}

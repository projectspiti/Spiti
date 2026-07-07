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
class WeeklyDayPlanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsWeeklyPlanThroughRestEndpoint() throws Exception {
        String requestBody = """
                {
                  "days": [
                    {
                      "date": "2026-07-06",
                      "dayType": "OFFICE",
                      "priceTier": "STANDARD"
                    },
                    {
                      "date": "2026-07-07",
                      "dayType": "TRAVEL",
                      "priceTier": "BUDGET"
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/day-plans/weekly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days[0].dayType").value("OFFICE"))
                .andExpect(jsonPath("$.days[0].dailyBudgetInRupees").value(200))
                .andExpect(jsonPath("$.days[0].packagingRule", containsString("Leak-proof")))
                .andExpect(jsonPath("$.days[1].dayType").value("TRAVEL"))
                .andExpect(jsonPath("$.days[1].dailyBudgetInRupees").value(100))
                .andExpect(jsonPath("$.days[1].mealRule", containsString("Portable")));
    }
}

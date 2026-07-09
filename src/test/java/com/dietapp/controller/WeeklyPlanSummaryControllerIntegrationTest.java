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
class WeeklyPlanSummaryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void summarizesWeeklyPlanTotals() throws Exception {
        mockMvc.perform(post("/api/plan-summary/weekly")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "weekName": "Week 1",
                                  "days": [
                                    {
                                      "dayName": "Monday",
                                      "meals": [
                                        {
                                          "mealName": "Idli bowl",
                                          "calories": 320,
                                          "priceInRupees": 90
                                        },
                                        {
                                          "mealName": "Paneer bowl",
                                          "calories": 560,
                                          "priceInRupees": 275
                                        }
                                      ]
                                    },
                                    {
                                      "dayName": "Tuesday",
                                      "meals": [
                                        {
                                          "mealName": "Dal soup",
                                          "calories": 280,
                                          "priceInRupees": 120
                                        }
                                      ]
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weekName").value("Week 1"))
                .andExpect(jsonPath("$.totalCalories").value(1160))
                .andExpect(jsonPath("$.totalPriceInRupees").value(485))
                .andExpect(jsonPath("$.days", hasSize(2)))
                .andExpect(jsonPath("$.days[0].totalCalories").value(880))
                .andExpect(jsonPath("$.days[0].meals", hasSize(2)));
    }
}

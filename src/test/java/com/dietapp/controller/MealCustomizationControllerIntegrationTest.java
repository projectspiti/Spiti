package com.dietapp.controller;

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
class MealCustomizationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void customizesMealFromPostRequest() throws Exception {
        mockMvc.perform(post("/api/meals/customize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "mealName": "Paneer Bowl",
                                  "calories": 420,
                                  "protein": 32,
                                  "carbs": 45,
                                  "fat": 14,
                                  "priceInRupees": 165,
                                  "addons": ["EXTRA_PROTEIN", "ADD_CURD", "MILLET_SWAP"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paneer Bowl + Extra Protein + Curd + Millet Swap"))
                .andExpect(jsonPath("$.calories").value(560))
                .andExpect(jsonPath("$.protein").value(62))
                .andExpect(jsonPath("$.carbs").value(41))
                .andExpect(jsonPath("$.fat").value(19))
                .andExpect(jsonPath("$.priceInRupees").value(275))
                .andExpect(jsonPath("$.appliedAddons", contains("Extra Protein", "Curd", "Millet Swap")));
    }
}

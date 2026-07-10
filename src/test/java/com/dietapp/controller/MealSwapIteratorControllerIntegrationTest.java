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
class MealSwapIteratorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsMealSwapSuggestionsUsingIterator() throws Exception {
        mockMvc.perform(post("/api/meal-swaps/suggestions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "currentMealName": "Paneer Rice Bowl",
                                  "nutritionMode": "VEG",
                                  "maxCalories": 450,
                                  "minimumProtein": 20
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestions[0].mealName").value("Tofu Bowl"))
                .andExpect(jsonPath("$.suggestions[1].mealName").value("Sprout Salad"));
    }
}

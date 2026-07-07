package com.dietapp.controller;

import static org.hamcrest.Matchers.hasItem;
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
class NutritionModeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsVegNutritionModePlanThroughRestEndpoint() throws Exception {
        String requestBody = """
                {
                  "nutritionMode": "VEG"
                }
                """;

        mockMvc.perform(post("/api/nutrition-modes/plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nutritionMode").value("VEG"))
                .andExpect(jsonPath("$.mealBuilderName").value("VegMealBuilder"))
                .andExpect(jsonPath("$.macroBlockSetName").value("VegMacroBlockSet"))
                .andExpect(jsonPath("$.kitchenMenuName").value("VegKitchenMenu"))
                .andExpect(jsonPath("$.proteinSources", hasItem("paneer")))
                .andExpect(jsonPath("$.kitchenItems", hasItem("Paneer protein bowl")));
    }
}

package com.dietapp.kitchen.controller;

import com.dietapp.kitchen.service.PricingConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KitchenAdminDishControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void duplicatesDishTemplateFromPostRequest() throws Exception {
        mockMvc.perform(post("/api/kitchen-admin/dish-templates/duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "sourceTemplateCode": "VEG_PANEER_BOWL",
                                  "newTemplateCode": "VEG_SPICY_PANEER_BOWL",
                                  "newDishName": "Spicy Paneer Protein Bowl",
                                  "newBasePriceInRupees": 190
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sourceTemplateCode").value("VEG_PANEER_BOWL"))
                .andExpect(jsonPath("$.newTemplateCode").value("VEG_SPICY_PANEER_BOWL"))
                .andExpect(jsonPath("$.dishName").value("Spicy Paneer Protein Bowl"))
                .andExpect(jsonPath("$.nutritionMode").value("VEG"))
                .andExpect(jsonPath("$.priceTier").value("STANDARD"))
                .andExpect(jsonPath("$.basePriceInRupees").value(190))
                .andExpect(jsonPath("$.finalPriceInRupees").value(230))
                .andExpect(jsonPath("$.ingredients", contains("paneer", "brown rice", "cucumber", "mint chutney")))
                .andExpect(jsonPath("$.tags", contains("high-protein", "veg", "lunch")));
    }

    @Test
    void pricingConfigServiceIsSingletonBeanInSpringContext() {
        PricingConfigService first = applicationContext.getBean(PricingConfigService.class);
        PricingConfigService second = applicationContext.getBean(PricingConfigService.class);

        assertThat(first).isSameAs(second);
    }
}

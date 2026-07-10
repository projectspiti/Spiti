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
class OrderReportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generatesInvoiceUsingVisitor() throws Exception {
        mockMvc.perform(post("/api/order-reports/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "orderId": "ORDER-302",
                                  "reportType": "INVOICE",
                                  "meals": [
                                    {
                                      "mealName": "Tofu Bowl",
                                      "calories": 420,
                                      "protein": 34,
                                      "priceInRupees": 180
                                    }
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reportType").value("INVOICE"))
                .andExpect(jsonPath("$.totalPriceInRupees").value(180));
    }
}

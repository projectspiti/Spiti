package com.dietapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MenuImageControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cachesMenuImageThroughProxy() throws Exception {
        mockMvc.perform(get("/api/menu/images/proxy_test_bowl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishCode").value("PROXY_TEST_BOWL"))
                .andExpect(jsonPath("$.source").value("BACKEND"));

        mockMvc.perform(get("/api/menu/images/proxy_test_bowl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dishCode").value("PROXY_TEST_BOWL"))
                .andExpect(jsonPath("$.source").value("CACHE"));
    }
}

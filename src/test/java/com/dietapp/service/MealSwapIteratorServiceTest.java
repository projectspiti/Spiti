package com.dietapp.service;

import com.dietapp.model.MealSwapSuggestionRequest;
import com.dietapp.model.MealSwapSuggestionResponse;
import com.dietapp.model.NutritionMode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MealSwapIteratorServiceTest {

    @Test
    void returnsOnlyMatchingSwapSuggestions() {
        MealSwapIteratorService service = new MealSwapIteratorService();

        MealSwapSuggestionResponse response = service.suggestions(new MealSwapSuggestionRequest(
                "Paneer Rice Bowl",
                NutritionMode.VEG,
                450,
                20
        ));

        assertThat(response.suggestions())
                .extracting("mealName")
                .containsExactly("Tofu Bowl", "Sprout Salad");
    }
}

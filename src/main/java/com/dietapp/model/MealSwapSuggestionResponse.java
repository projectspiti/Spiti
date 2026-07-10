package com.dietapp.model;

import java.util.List;

public record MealSwapSuggestionResponse(
        String currentMealName,
        List<MealSwapSuggestion> suggestions
) {
}

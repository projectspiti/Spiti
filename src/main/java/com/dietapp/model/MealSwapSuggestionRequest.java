package com.dietapp.model;

public record MealSwapSuggestionRequest(
        String currentMealName,
        NutritionMode nutritionMode,
        int maxCalories,
        int minimumProtein
) {
}

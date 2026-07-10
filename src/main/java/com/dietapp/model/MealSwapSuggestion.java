package com.dietapp.model;

public record MealSwapSuggestion(
        String mealName,
        NutritionMode nutritionMode,
        int calories,
        int protein,
        int priceInRupees
) {
}

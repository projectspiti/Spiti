package com.dietapp.mealplan.controller;

import com.dietapp.nutrition.model.NutritionMode;

public record MealPlanBuilderRequest(
        Long userId,
        int targetCalories,
        NutritionMode nutritionMode,
        boolean deliveryRequired
) {
}

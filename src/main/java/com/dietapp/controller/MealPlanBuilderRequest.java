package com.dietapp.controller;

import com.dietapp.model.NutritionMode;

public record MealPlanBuilderRequest(
        Long userId,
        int targetCalories,
        NutritionMode nutritionMode,
        boolean deliveryRequired
) {
}

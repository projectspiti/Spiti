package com.dietapp.mealplan.model;

import com.dietapp.nutrition.model.NutritionMode;

import java.util.List;

public record MealPlanSummary(
        Long userId,
        int targetCalories,
        NutritionMode nutritionMode,
        String goal,
        List<String> meals,
        List<String> snacks,
        boolean deliveryRequired,
        String deliverySlot,
        String notes
) {
    public static MealPlanSummary from(MealPlan mealPlan) {
        return new MealPlanSummary(
                mealPlan.getUserId(),
                mealPlan.getTargetCalories(),
                mealPlan.getNutritionMode(),
                mealPlan.getGoal(),
                mealPlan.getMeals(),
                mealPlan.getSnacks(),
                mealPlan.isDeliveryRequired(),
                mealPlan.getDeliverySlot(),
                mealPlan.getNotes()
        );
    }
}

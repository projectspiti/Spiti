package com.dietapp.model;

import com.dietapp.controller.CalorieTargetResponse;
import com.dietapp.controller.NutritionModePlanResponse;
import com.dietapp.controller.WeeklyDayPlanResponse;

public record OnboardingResponse(
        Long userId,
        CalorieTargetResponse calorieTarget,
        NutritionModePlanResponse nutritionPlan,
        WeeklyDayPlanResponse weeklyPlan,
        MealPlanSummary starterMealPlan
) {
}

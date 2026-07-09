package com.dietapp.model;

import java.util.List;

public record WeeklyPlanSummaryResponse(
        String weekName,
        int totalCalories,
        int totalPriceInRupees,
        List<DaySummaryResponse> days
) {

    public record DaySummaryResponse(
            String dayName,
            int totalCalories,
            int totalPriceInRupees,
            List<MealSummaryResponse> meals
    ) {
    }

    public record MealSummaryResponse(
            String mealName,
            int calories,
            int priceInRupees
    ) {
    }
}

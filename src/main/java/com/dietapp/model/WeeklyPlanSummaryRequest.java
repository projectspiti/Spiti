package com.dietapp.model;

import java.util.List;

public record WeeklyPlanSummaryRequest(
        String weekName,
        List<DaySummaryRequest> days
) {

    public record DaySummaryRequest(
            String dayName,
            List<MealSummaryRequest> meals
    ) {
    }

    public record MealSummaryRequest(
            String mealName,
            int calories,
            int priceInRupees
    ) {
    }
}

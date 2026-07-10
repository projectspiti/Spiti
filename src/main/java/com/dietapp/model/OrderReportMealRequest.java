package com.dietapp.model;

public record OrderReportMealRequest(
        String mealName,
        int calories,
        int protein,
        int priceInRupees
) {
}

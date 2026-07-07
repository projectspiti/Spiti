package com.dietapp.model;

import java.time.LocalDate;

public record DayPlan(
        LocalDate date,
        DayType dayType,
        PriceTier priceTier,
        int dailyBudgetInRupees,
        String mealRule,
        String packagingRule,
        String deliveryRule
) {
}

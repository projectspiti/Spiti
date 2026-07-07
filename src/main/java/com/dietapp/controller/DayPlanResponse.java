package com.dietapp.controller;

import com.dietapp.model.DayPlan;
import com.dietapp.model.DayType;
import com.dietapp.model.PriceTier;
import java.time.LocalDate;

public record DayPlanResponse(
        LocalDate date,
        DayType dayType,
        PriceTier priceTier,
        int dailyBudgetInRupees,
        String mealRule,
        String packagingRule,
        String deliveryRule
) {

    public static DayPlanResponse from(DayPlan dayPlan) {
        return new DayPlanResponse(
                dayPlan.date(),
                dayPlan.dayType(),
                dayPlan.priceTier(),
                dayPlan.dailyBudgetInRupees(),
                dayPlan.mealRule(),
                dayPlan.packagingRule(),
                dayPlan.deliveryRule()
        );
    }
}

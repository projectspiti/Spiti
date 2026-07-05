package com.dietapp.dayplan.api;

import com.dietapp.dayplan.model.DayPlan;
import com.dietapp.dayplan.model.DayType;
import com.dietapp.dayplan.model.PriceTier;
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

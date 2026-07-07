package com.dietapp.controller;

import com.dietapp.model.DayPlan;
import java.util.List;

public record WeeklyDayPlanResponse(
        List<DayPlanResponse> days
) {

    public static WeeklyDayPlanResponse from(List<DayPlan> dayPlans) {
        return new WeeklyDayPlanResponse(
                dayPlans.stream()
                        .map(DayPlanResponse::from)
                        .toList()
        );
    }
}

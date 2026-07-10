package com.dietapp.model;

public record DietPlanTemplateRequest(
        Long userId,
        DietPlanGoal goal,
        DayType dayType,
        boolean intermittentFastingEnabled,
        String fastingWindow
) {
}

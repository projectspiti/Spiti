package com.dietapp.model;

import java.util.List;

public record DietPlanTemplateResponse(
        Long userId,
        DietPlanGoal goal,
        int calorieTarget,
        List<String> meals,
        String dayTypeRule,
        String fastingRule,
        List<String> executedSteps
) {
}

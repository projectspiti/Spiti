package com.dietapp.calorie.controller;

import com.dietapp.calorie.model.CalorieTarget;

public record CalorieTargetResponse(
        long bmr,
        long maintenanceCalories,
        long targetCalories,
        String bmrStrategy,
        String goalStrategy,
        String explanation
) {

    public static CalorieTargetResponse from(CalorieTarget target) {
        return new CalorieTargetResponse(
                target.bmr(),
                target.maintenanceCalories(),
                target.targetCalories(),
                target.bmrStrategy(),
                target.goalStrategy(),
                target.explanation()
        );
    }
}

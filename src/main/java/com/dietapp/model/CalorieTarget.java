package com.dietapp.model;

public record CalorieTarget(
        long bmr,
        long maintenanceCalories,
        long targetCalories,
        String bmrStrategy,
        String goalStrategy,
        String explanation
) {
}

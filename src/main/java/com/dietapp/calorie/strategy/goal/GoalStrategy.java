package com.dietapp.calorie.strategy.goal;

import com.dietapp.calorie.model.GoalType;

public interface GoalStrategy {

    GoalType goalType();

    String name();

    double applyTo(double maintenanceCalories);
}

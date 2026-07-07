package com.dietapp.service.strategy.goal;

import com.dietapp.model.GoalType;

public interface GoalStrategy {

    GoalType goalType();

    String name();

    double applyTo(double maintenanceCalories);
}

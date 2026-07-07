package com.dietapp.calorie.strategy.goal;

import org.springframework.stereotype.Component;

import com.dietapp.calorie.model.GoalType;

@Component
public class LeanMuscleGoalStrategy implements GoalStrategy {

    @Override
    public GoalType goalType() {
        return GoalType.LEAN_MUSCLE;
    }

    @Override
    public String name() {
        return "LEAN_MUSCLE_5_PERCENT_SURPLUS";
    }

    @Override
    public double applyTo(double maintenanceCalories) {
        return maintenanceCalories * 1.05;
    }
}

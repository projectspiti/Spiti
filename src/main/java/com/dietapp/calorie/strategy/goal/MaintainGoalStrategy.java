package com.dietapp.calorie.strategy.goal;

import com.dietapp.calorie.model.GoalType;
import org.springframework.stereotype.Component;

@Component
public class MaintainGoalStrategy implements GoalStrategy {

    @Override
    public GoalType goalType() {
        return GoalType.MAINTAIN;
    }

    @Override
    public String name() {
        return "MAINTAIN";
    }

    @Override
    public double applyTo(double maintenanceCalories) {
        return maintenanceCalories;
    }
}

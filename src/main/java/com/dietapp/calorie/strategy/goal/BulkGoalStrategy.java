package com.dietapp.calorie.strategy.goal;

import com.dietapp.calorie.model.GoalType;
import org.springframework.stereotype.Component;

@Component
public class BulkGoalStrategy implements GoalStrategy {

    @Override
    public GoalType goalType() {
        return GoalType.BULK;
    }

    @Override
    public String name() {
        return "BULK_15_PERCENT_SURPLUS";
    }

    @Override
    public double applyTo(double maintenanceCalories) {
        return maintenanceCalories * 1.15;
    }
}

package com.dietapp.calorie.strategy.goal;

import com.dietapp.calorie.model.GoalType;
import org.springframework.stereotype.Component;

@Component
public class CutGoalStrategy implements GoalStrategy {

    @Override
    public GoalType goalType() {
        return GoalType.CUT;
    }

    @Override
    public String name() {
        return "CUT_20_PERCENT_DEFICIT";
    }

    @Override
    public double applyTo(double maintenanceCalories) {
        return maintenanceCalories * 0.80;
    }
}

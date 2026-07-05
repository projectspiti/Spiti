package com.dietapp.calorie.service;

import com.dietapp.calorie.model.CalorieTarget;
import com.dietapp.calorie.model.UserProfile;
import com.dietapp.calorie.strategy.bmr.BmrStrategy;
import com.dietapp.calorie.strategy.goal.GoalStrategy;
import org.springframework.stereotype.Service;

@Service
public class CalorieEngine {

    private final BmrStrategyResolver bmrStrategyResolver;
    private final GoalStrategyResolver goalStrategyResolver;

    public CalorieEngine(BmrStrategyResolver bmrStrategyResolver, GoalStrategyResolver goalStrategyResolver) {
        this.bmrStrategyResolver = bmrStrategyResolver;
        this.goalStrategyResolver = goalStrategyResolver;
    }

    public CalorieTarget calculateTarget(UserProfile userProfile) {
        BmrStrategy bmrStrategy = bmrStrategyResolver.resolve(userProfile);
        GoalStrategy goalStrategy = goalStrategyResolver.resolve(userProfile.goalType());

        double bmr = bmrStrategy.calculateBmr(userProfile);
        double maintenanceCalories = bmr * userProfile.activityLevel().getMultiplier();
        double targetCalories = goalStrategy.applyTo(maintenanceCalories);

        return new CalorieTarget(
                Math.round(bmr),
                Math.round(maintenanceCalories),
                Math.round(targetCalories),
                bmrStrategy.name(),
                goalStrategy.name(),
                buildExplanation(bmrStrategy, goalStrategy)
        );
    }

    private String buildExplanation(BmrStrategy bmrStrategy, GoalStrategy goalStrategy) {
        return "BMR calculated using " + bmrStrategy.name()
                + ", then maintenance calories adjusted using " + goalStrategy.name() + ".";
    }
}

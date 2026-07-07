package com.dietapp.calorie.service;

import org.springframework.stereotype.Component;

import com.dietapp.calorie.model.GoalType;
import com.dietapp.calorie.strategy.goal.GoalStrategy;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class GoalStrategyResolver {

    private final Map<GoalType, GoalStrategy> strategiesByGoalType;

    public GoalStrategyResolver(List<GoalStrategy> strategies) {
        this.strategiesByGoalType = new EnumMap<>(GoalType.class);
        for (GoalStrategy strategy : strategies) {
            strategiesByGoalType.put(strategy.goalType(), strategy);
        }
    }

    public GoalStrategy resolve(GoalType goalType) {
        GoalStrategy strategy = strategiesByGoalType.get(goalType);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported goal type: " + goalType);
        }
        return strategy;
    }
}

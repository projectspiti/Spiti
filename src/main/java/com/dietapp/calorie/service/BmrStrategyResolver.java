package com.dietapp.calorie.service;

import com.dietapp.calorie.model.UserProfile;
import com.dietapp.calorie.strategy.bmr.BmrStrategy;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BmrStrategyResolver {

    private final List<BmrStrategy> strategies;

    public BmrStrategyResolver(List<BmrStrategy> strategies) {
        this.strategies = strategies.stream()
                .sorted(Comparator.comparingInt(BmrStrategy::priority).reversed())
                .toList();
    }

    public BmrStrategy resolve(UserProfile userProfile) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(userProfile))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No BMR strategy supports this profile"));
    }
}

package com.dietapp.service;

import org.springframework.stereotype.Component;

import com.dietapp.model.UserProfile;
import com.dietapp.service.strategy.bmr.BmrStrategy;
import java.util.Comparator;
import java.util.List;

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

package com.dietapp.calorie.strategy.bmr;

import org.springframework.stereotype.Component;

import com.dietapp.calorie.model.UserProfile;

@Component
public class KatchMcArdleBmrStrategy implements BmrStrategy {

    @Override
    public String name() {
        return "KATCH_MC_ARDLE";
    }

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public boolean supports(UserProfile userProfile) {
        return userProfile.bodyFatPercentage() != null;
    }

    @Override
    public double calculateBmr(UserProfile userProfile) {
        if (userProfile.bodyFatPercentage() == null) {
            throw new IllegalArgumentException("bodyFatPercentage is required for Katch-McArdle BMR");
        }

        double leanBodyMassKg = userProfile.weightKg() * (1 - userProfile.bodyFatPercentage() / 100);
        return 370 + 21.6 * leanBodyMassKg;
    }
}

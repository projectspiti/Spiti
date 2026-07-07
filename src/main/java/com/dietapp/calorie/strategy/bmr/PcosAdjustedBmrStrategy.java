package com.dietapp.calorie.strategy.bmr;

import org.springframework.stereotype.Component;

import com.dietapp.calorie.model.MedicalCondition;
import com.dietapp.calorie.model.UserProfile;

@Component
public class PcosAdjustedBmrStrategy implements BmrStrategy {

    private static final double REVIEWED_ADJUSTMENT_FACTOR = 0.95;

    private final MifflinStJeorBmrStrategy mifflinStJeorBmrStrategy;

    public PcosAdjustedBmrStrategy(MifflinStJeorBmrStrategy mifflinStJeorBmrStrategy) {
        this.mifflinStJeorBmrStrategy = mifflinStJeorBmrStrategy;
    }

    @Override
    public String name() {
        return "PCOS_ADJUSTED";
    }

    @Override
    public int priority() {
        return 20;
    }

    @Override
    public boolean supports(UserProfile userProfile) {
        return userProfile.hasMedicalCondition(MedicalCondition.PCOS);
    }

    @Override
    public double calculateBmr(UserProfile userProfile) {
        return mifflinStJeorBmrStrategy.calculateBmr(userProfile) * REVIEWED_ADJUSTMENT_FACTOR;
    }
}

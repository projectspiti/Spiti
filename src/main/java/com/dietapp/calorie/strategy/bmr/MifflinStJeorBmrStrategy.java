package com.dietapp.calorie.strategy.bmr;

import org.springframework.stereotype.Component;

import com.dietapp.calorie.model.Gender;
import com.dietapp.calorie.model.UserProfile;

@Component
public class MifflinStJeorBmrStrategy implements BmrStrategy {

    @Override
    public String name() {
        return "MIFFLIN_ST_JEOR";
    }

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean supports(UserProfile userProfile) {
        return true;
    }

    @Override
    public double calculateBmr(UserProfile userProfile) {
        double base = 10 * userProfile.weightKg()
                + 6.25 * userProfile.heightCm()
                - 5 * userProfile.age();

        if (userProfile.gender() == Gender.MALE) {
            return base + 5;
        }

        return base - 161;
    }
}

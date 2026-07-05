package com.dietapp.calorie.api;

import com.dietapp.calorie.model.ActivityLevel;
import com.dietapp.calorie.model.Gender;
import com.dietapp.calorie.model.GoalType;
import com.dietapp.calorie.model.MedicalCondition;
import com.dietapp.calorie.model.UserProfile;
import java.util.Set;

public record CalorieTargetRequest(
        int age,
        double weightKg,
        double heightCm,
        Gender gender,
        ActivityLevel activityLevel,
        GoalType goalType,
        Double bodyFatPercentage,
        Set<MedicalCondition> medicalConditions
) {

    public UserProfile toUserProfile() {
        return new UserProfile(
                age,
                weightKg,
                heightCm,
                gender,
                activityLevel,
                goalType,
                bodyFatPercentage,
                medicalConditions
        );
    }
}

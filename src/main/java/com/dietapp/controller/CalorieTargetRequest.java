package com.dietapp.controller;

import com.dietapp.model.ActivityLevel;
import com.dietapp.model.Gender;
import com.dietapp.model.GoalType;
import com.dietapp.model.MedicalCondition;
import com.dietapp.model.UserProfile;
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

package com.dietapp.calorie.model;

import java.util.Set;

public record UserProfile(
        int age,
        double weightKg,
        double heightCm,
        Gender gender,
        ActivityLevel activityLevel,
        GoalType goalType,
        Double bodyFatPercentage,
        Set<MedicalCondition> medicalConditions
) {

    public UserProfile {
        if (age <= 0) {
            throw new IllegalArgumentException("age must be positive");
        }
        if (weightKg <= 0) {
            throw new IllegalArgumentException("weightKg must be positive");
        }
        if (heightCm <= 0) {
            throw new IllegalArgumentException("heightCm must be positive");
        }
        if (gender == null) {
            throw new IllegalArgumentException("gender is required");
        }
        if (activityLevel == null) {
            throw new IllegalArgumentException("activityLevel is required");
        }
        if (goalType == null) {
            throw new IllegalArgumentException("goalType is required");
        }
        if (bodyFatPercentage != null && (bodyFatPercentage <= 0 || bodyFatPercentage >= 100)) {
            throw new IllegalArgumentException("bodyFatPercentage must be between 0 and 100");
        }

        medicalConditions = medicalConditions == null ? Set.of() : Set.copyOf(medicalConditions);
    }

    public boolean hasMedicalCondition(MedicalCondition medicalCondition) {
        return medicalConditions.contains(medicalCondition);
    }
}

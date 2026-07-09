package com.dietapp.model;

import com.dietapp.service.WeeklyDayPlanner;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record OnboardingRequest(
        Long userId,
        int age,
        double weightKg,
        double heightCm,
        Gender gender,
        ActivityLevel activityLevel,
        GoalType goalType,
        Double bodyFatPercentage,
        Set<MedicalCondition> medicalConditions,
        NutritionMode nutritionMode,
        List<DayPreference> days,
        boolean deliveryRequired
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

    public List<WeeklyDayPlanner.DayPlanInput> toDayPlanInputs() {
        return days.stream()
                .map(day -> new WeeklyDayPlanner.DayPlanInput(day.date(), day.dayType(), day.priceTier()))
                .toList();
    }

    public record DayPreference(
            LocalDate date,
            DayType dayType,
            PriceTier priceTier
    ) {
    }
}

package com.dietapp.service;

import com.dietapp.controller.CalorieTargetResponse;
import com.dietapp.controller.NutritionModePlanResponse;
import com.dietapp.controller.WeeklyDayPlanResponse;
import com.dietapp.model.CalorieTarget;
import com.dietapp.model.DayPlan;
import com.dietapp.model.MealPlanSummary;
import com.dietapp.model.NutritionModePlan;
import com.dietapp.model.OnboardingRequest;
import com.dietapp.model.OnboardingResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OnboardingFacade {

    private static final Logger log = LoggerFactory.getLogger(OnboardingFacade.class);

    private final CalorieEngine calorieEngine;
    private final NutritionModePlanService nutritionModePlanService;
    private final WeeklyDayPlanner weeklyDayPlanner;
    private final MealPlanBuilderService mealPlanBuilderService;

    public OnboardingFacade(
            CalorieEngine calorieEngine,
            NutritionModePlanService nutritionModePlanService,
            WeeklyDayPlanner weeklyDayPlanner,
            MealPlanBuilderService mealPlanBuilderService
    ) {
        this.calorieEngine = calorieEngine;
        this.nutritionModePlanService = nutritionModePlanService;
        this.weeklyDayPlanner = weeklyDayPlanner;
        this.mealPlanBuilderService = mealPlanBuilderService;
    }

    public OnboardingResponse completeOnboarding(OnboardingRequest request) {
        log.info("Starting onboarding userId={}, nutritionMode={}, days={}",
                request.userId(), request.nutritionMode(), request.days().size());

        CalorieTarget calorieTarget = calorieEngine.calculateTarget(request.toUserProfile());
        log.info("Onboarding calorie target calculated userId={}, targetCalories={}",
                request.userId(), calorieTarget.targetCalories());

        NutritionModePlan nutritionPlan = nutritionModePlanService.createNutritionModePlan(request.nutritionMode());
        log.info("Onboarding nutrition plan selected userId={}, nutritionMode={}",
                request.userId(), nutritionPlan.nutritionMode());

        List<DayPlan> weeklyPlan = weeklyDayPlanner.createWeeklyPlan(request.toDayPlanInputs());
        log.info("Onboarding weekly plan created userId={}, days={}", request.userId(), weeklyPlan.size());

        MealPlanSummary starterMealPlan = mealPlanBuilderService.buildWeightLossPlan(
                request.userId(),
                Math.toIntExact(calorieTarget.targetCalories()),
                request.nutritionMode(),
                request.deliveryRequired()
        );
        log.info("Onboarding starter meal plan built userId={}, deliveryRequired={}",
                starterMealPlan.userId(), starterMealPlan.deliveryRequired());

        log.info("Onboarding completed userId={}", request.userId());
        return new OnboardingResponse(
                request.userId(),
                CalorieTargetResponse.from(calorieTarget),
                NutritionModePlanResponse.from(nutritionPlan),
                WeeklyDayPlanResponse.from(weeklyPlan),
                starterMealPlan
        );
    }
}

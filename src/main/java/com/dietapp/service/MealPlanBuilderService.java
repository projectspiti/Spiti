package com.dietapp.service;

import com.dietapp.model.MealPlan;
import com.dietapp.model.MealPlanSummary;
import com.dietapp.model.NutritionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MealPlanBuilderService {
    private static final Logger log = LoggerFactory.getLogger(MealPlanBuilderService.class);

    public MealPlanSummary buildWeightLossPlan(
            Long userId,
            int targetCalories,
            NutritionMode nutritionMode,
            boolean deliveryRequired
    ) {
        log.info("Building starter meal plan userId={}, targetCalories={}, nutritionMode={}, deliveryRequired={}",
                userId, targetCalories, nutritionMode, deliveryRequired);

        MealPlan.Builder builder = MealPlan.builder(userId, targetCalories, nutritionMode)
                .goal("WEIGHT_LOSS")
                .addMeal(nutritionMode == NutritionMode.VEG ? "Paneer protein bowl" : "Grilled chicken protein bowl")
                .addMeal("Low-calorie dal soup")
                .addSnack("Greek yogurt")
                .notes("Starter meal plan generated for onboarding");

        log.info("Starter meal plan base meals selected userId={}", userId);

        if (deliveryRequired) {
            builder.deliveryRequired(true)
                    .deliverySlot("LUNCH_SLOT");
            log.info("Starter meal plan delivery slot assigned userId={}, deliverySlot={}", userId, "LUNCH_SLOT");
        }

        MealPlan mealPlan = builder.build();
        log.info("Starter meal plan completed userId={}, meals={}, snacks={}",
                mealPlan.getUserId(), mealPlan.getMeals().size(), mealPlan.getSnacks().size());

        return MealPlanSummary.from(mealPlan);
    }
}

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
        log.info("Builder Pattern flow: starting MealPlan builder for userId={}, targetCalories={}, nutritionMode={}",
                userId, targetCalories, nutritionMode);

        MealPlan.Builder builder = MealPlan.builder(userId, targetCalories, nutritionMode)
                .goal("WEIGHT_LOSS")
                .addMeal(nutritionMode == NutritionMode.VEG ? "Paneer protein bowl" : "Grilled chicken protein bowl")
                .addMeal("Low-calorie dal soup")
                .addSnack("Greek yogurt")
                .notes("Created through manual Builder Pattern");

        log.info("Builder Pattern flow: optional fields added step by step");

        if (deliveryRequired) {
            builder.deliveryRequired(true)
                    .deliverySlot("LUNCH_SLOT");
            log.info("Builder Pattern flow: delivery fields added because deliveryRequired=true");
        }

        MealPlan mealPlan = builder.build();
        log.info("Builder Pattern flow: build() created immutable MealPlan for userId={}", mealPlan.getUserId());

        return MealPlanSummary.from(mealPlan);
    }
}

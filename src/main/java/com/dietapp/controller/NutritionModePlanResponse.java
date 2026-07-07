package com.dietapp.controller;

import com.dietapp.model.NutritionMode;
import com.dietapp.model.NutritionModePlan;
import java.util.List;

public record NutritionModePlanResponse(
        NutritionMode nutritionMode,
        String mealBuilderName,
        List<String> sampleMeals,
        String macroBlockSetName,
        List<String> proteinSources,
        List<String> carbSources,
        String kitchenMenuName,
        List<String> kitchenItems
) {

    public static NutritionModePlanResponse from(NutritionModePlan plan) {
        return new NutritionModePlanResponse(
                plan.nutritionMode(),
                plan.mealBuilderName(),
                plan.sampleMeals(),
                plan.macroBlockSetName(),
                plan.proteinSources(),
                plan.carbSources(),
                plan.kitchenMenuName(),
                plan.kitchenItems()
        );
    }
}

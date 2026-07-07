package com.dietapp.model;

import java.util.List;

public record NutritionModePlan(
        NutritionMode nutritionMode,
        String mealBuilderName,
        List<String> sampleMeals,
        String macroBlockSetName,
        List<String> proteinSources,
        List<String> carbSources,
        String kitchenMenuName,
        List<String> kitchenItems
) {
}

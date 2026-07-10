package com.dietapp.service.meal.iterator;

import com.dietapp.model.MealSwapSuggestion;
import com.dietapp.model.MealSwapSuggestionRequest;
import com.dietapp.model.NutritionMode;
import java.util.List;

public class MealSwapSuggestionCollection {

    private final List<MealSwapSuggestion> meals = List.of(
            new MealSwapSuggestion("Tofu Bowl", NutritionMode.VEG, 420, 34, 180),
            new MealSwapSuggestion("Sprout Salad", NutritionMode.VEG, 310, 22, 140),
            new MealSwapSuggestion("Paneer Rice Bowl", NutritionMode.VEG, 620, 38, 230),
            new MealSwapSuggestion("Chicken Millet Bowl", NutritionMode.NON_VEG, 510, 48, 260),
            new MealSwapSuggestion("Egg Protein Bowl", NutritionMode.NON_VEG, 440, 36, 190)
    );

    public MealIterator iterator(MealSwapSuggestionRequest request) {
        return new FilteredMealIterator(meals, request);
    }
}

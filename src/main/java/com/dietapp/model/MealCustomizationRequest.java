package com.dietapp.model;

import java.util.List;

public record MealCustomizationRequest(
        String mealName,
        int calories,
        int protein,
        int carbs,
        int fat,
        int priceInRupees,
        List<MealAddonType> addons
) {
}

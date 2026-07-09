package com.dietapp.model;

import java.util.List;

public record MealCustomizationResponse(
        String name,
        int calories,
        int protein,
        int carbs,
        int fat,
        int priceInRupees,
        List<String> appliedAddons
) {
}

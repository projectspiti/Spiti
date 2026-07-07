package com.dietapp.model;

import java.util.List;

public record DishTemplateCopy(
        String sourceTemplateCode,
        String newTemplateCode,
        String dishName,
        NutritionMode nutritionMode,
        DishPriceTier priceTier,
        int basePriceInRupees,
        int finalPriceInRupees,
        List<String> ingredients,
        List<String> tags,
        String packagingRule
) {
    public static DishTemplateCopy from(String sourceTemplateCode, DishTemplate duplicate, int finalPriceInRupees) {
        return new DishTemplateCopy(
                sourceTemplateCode,
                duplicate.getTemplateCode(),
                duplicate.getDishName(),
                duplicate.getNutritionMode(),
                duplicate.getPriceTier(),
                duplicate.getBasePriceInRupees(),
                finalPriceInRupees,
                duplicate.getIngredients(),
                duplicate.getTags(),
                duplicate.getPackagingRule()
        );
    }
}

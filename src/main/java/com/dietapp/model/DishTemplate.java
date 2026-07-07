package com.dietapp.model;

import java.util.List;
import java.util.Objects;

public final class DishTemplate {
    private final String templateCode;
    private final String dishName;
    private final NutritionMode nutritionMode;
    private final DishPriceTier priceTier;
    private final int basePriceInRupees;
    private final List<String> ingredients;
    private final List<String> tags;
    private final String packagingRule;

    public DishTemplate(
            String templateCode,
            String dishName,
            NutritionMode nutritionMode,
            DishPriceTier priceTier,
            int basePriceInRupees,
            List<String> ingredients,
            List<String> tags,
            String packagingRule
    ) {
        this.templateCode = Objects.requireNonNull(templateCode, "templateCode is required");
        this.dishName = Objects.requireNonNull(dishName, "dishName is required");
        this.nutritionMode = Objects.requireNonNull(nutritionMode, "nutritionMode is required");
        this.priceTier = Objects.requireNonNull(priceTier, "priceTier is required");
        this.basePriceInRupees = basePriceInRupees;
        this.ingredients = List.copyOf(Objects.requireNonNull(ingredients, "ingredients are required"));
        this.tags = List.copyOf(Objects.requireNonNull(tags, "tags are required"));
        this.packagingRule = Objects.requireNonNull(packagingRule, "packagingRule is required");
    }

    public DishTemplate duplicateForAdmin(String newTemplateCode, String newDishName, int newBasePriceInRupees) {
        return new DishTemplate(
                newTemplateCode,
                newDishName,
                nutritionMode,
                priceTier,
                newBasePriceInRupees,
                ingredients,
                tags,
                packagingRule
        );
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public String getDishName() {
        return dishName;
    }

    public NutritionMode getNutritionMode() {
        return nutritionMode;
    }

    public DishPriceTier getPriceTier() {
        return priceTier;
    }

    public int getBasePriceInRupees() {
        return basePriceInRupees;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getPackagingRule() {
        return packagingRule;
    }
}

package com.dietapp.nutrition.factory;

import com.dietapp.nutrition.model.NutritionMode;
import com.dietapp.nutrition.product.KitchenMenu;
import com.dietapp.nutrition.product.MacroBlockSet;
import com.dietapp.nutrition.product.MealBuilder;

public interface NutritionModeFactory {
    NutritionMode nutritionMode();

    MealBuilder createMealBuilder();

    MacroBlockSet createMacroBlockSet();

    KitchenMenu createKitchenMenu();
}

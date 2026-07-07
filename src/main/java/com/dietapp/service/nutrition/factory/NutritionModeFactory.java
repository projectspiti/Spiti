package com.dietapp.service.nutrition.factory;

import com.dietapp.model.NutritionMode;
import com.dietapp.service.nutrition.product.KitchenMenu;
import com.dietapp.service.nutrition.product.MacroBlockSet;
import com.dietapp.service.nutrition.product.MealBuilder;

public interface NutritionModeFactory {
    NutritionMode nutritionMode();

    MealBuilder createMealBuilder();

    MacroBlockSet createMacroBlockSet();

    KitchenMenu createKitchenMenu();
}

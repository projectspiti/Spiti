package com.dietapp.nutrition.factory;

import org.springframework.stereotype.Component;

import com.dietapp.nutrition.model.NutritionMode;
import com.dietapp.nutrition.product.KitchenMenu;
import com.dietapp.nutrition.product.MacroBlockSet;
import com.dietapp.nutrition.product.MealBuilder;
import com.dietapp.nutrition.product.nonveg.NonVegKitchenMenu;
import com.dietapp.nutrition.product.nonveg.NonVegMacroBlockSet;
import com.dietapp.nutrition.product.nonveg.NonVegMealBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NonVegNutritionModeFactory implements NutritionModeFactory {

    private static final Logger log = LoggerFactory.getLogger(NonVegNutritionModeFactory.class);

    @Override
    public NutritionMode nutritionMode() {
        return NutritionMode.NON_VEG;
    }

    @Override
    public MealBuilder createMealBuilder() {
        log.info("NonVegNutritionModeFactory.createMealBuilder() -> NonVegMealBuilder");
        return new NonVegMealBuilder();
    }

    @Override
    public MacroBlockSet createMacroBlockSet() {
        log.info("NonVegNutritionModeFactory.createMacroBlockSet() -> NonVegMacroBlockSet");
        return new NonVegMacroBlockSet();
    }

    @Override
    public KitchenMenu createKitchenMenu() {
        log.info("NonVegNutritionModeFactory.createKitchenMenu() -> NonVegKitchenMenu");
        return new NonVegKitchenMenu();
    }
}

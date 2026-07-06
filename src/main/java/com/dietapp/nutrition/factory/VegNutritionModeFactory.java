package com.dietapp.nutrition.factory;

import com.dietapp.nutrition.model.NutritionMode;
import com.dietapp.nutrition.product.KitchenMenu;
import com.dietapp.nutrition.product.MacroBlockSet;
import com.dietapp.nutrition.product.MealBuilder;
import com.dietapp.nutrition.product.veg.VegKitchenMenu;
import com.dietapp.nutrition.product.veg.VegMacroBlockSet;
import com.dietapp.nutrition.product.veg.VegMealBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VegNutritionModeFactory implements NutritionModeFactory {

    private static final Logger log = LoggerFactory.getLogger(VegNutritionModeFactory.class);

    @Override
    public NutritionMode nutritionMode() {
        return NutritionMode.VEG;
    }

    @Override
    public MealBuilder createMealBuilder() {
        log.info("VegNutritionModeFactory.createMealBuilder() -> VegMealBuilder");
        return new VegMealBuilder();
    }

    @Override
    public MacroBlockSet createMacroBlockSet() {
        log.info("VegNutritionModeFactory.createMacroBlockSet() -> VegMacroBlockSet");
        return new VegMacroBlockSet();
    }

    @Override
    public KitchenMenu createKitchenMenu() {
        log.info("VegNutritionModeFactory.createKitchenMenu() -> VegKitchenMenu");
        return new VegKitchenMenu();
    }
}

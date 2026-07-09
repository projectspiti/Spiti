package com.dietapp.service.nutrition.factory;

import org.springframework.stereotype.Component;

import com.dietapp.model.NutritionMode;
import com.dietapp.service.nutrition.product.KitchenMenu;
import com.dietapp.service.nutrition.product.MacroBlockSet;
import com.dietapp.service.nutrition.product.MealBuilder;
import com.dietapp.service.nutrition.product.veg.VegKitchenMenu;
import com.dietapp.service.nutrition.product.veg.VegMacroBlockSet;
import com.dietapp.service.nutrition.product.veg.VegMealBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class VegNutritionModeFactory implements NutritionModeFactory {

    private static final Logger log = LoggerFactory.getLogger(VegNutritionModeFactory.class);

    @Override
    public NutritionMode nutritionMode() {
        return NutritionMode.VEG;
    }

    @Override
    public MealBuilder createMealBuilder() {
        log.info("Selecting vegetarian meal suggestions");
        return new VegMealBuilder();
    }

    @Override
    public MacroBlockSet createMacroBlockSet() {
        log.info("Selecting vegetarian macro sources");
        return new VegMacroBlockSet();
    }

    @Override
    public KitchenMenu createKitchenMenu() {
        log.info("Selecting vegetarian kitchen menu");
        return new VegKitchenMenu();
    }
}

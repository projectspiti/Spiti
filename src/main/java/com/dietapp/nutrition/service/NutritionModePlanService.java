package com.dietapp.nutrition.service;

import com.dietapp.nutrition.factory.NutritionModeFactory;
import com.dietapp.nutrition.model.NutritionMode;
import com.dietapp.nutrition.model.NutritionModePlan;
import com.dietapp.nutrition.product.KitchenMenu;
import com.dietapp.nutrition.product.MacroBlockSet;
import com.dietapp.nutrition.product.MealBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NutritionModePlanService {

    private static final Logger log = LoggerFactory.getLogger(NutritionModePlanService.class);

    private final NutritionModeFactoryResolver factoryResolver;

    public NutritionModePlanService(NutritionModeFactoryResolver factoryResolver) {
        this.factoryResolver = factoryResolver;
    }

    public NutritionModePlan createNutritionModePlan(NutritionMode nutritionMode) {
        log.info("creating nutrition mode plan for nutritionMode={}", nutritionMode);
        NutritionModeFactory factory = factoryResolver.resolve(nutritionMode);

        MealBuilder mealBuilder = factory.createMealBuilder();
        MacroBlockSet macroBlockSet = factory.createMacroBlockSet();
        KitchenMenu kitchenMenu = factory.createKitchenMenu();

        log.info("created related object family: mealBuilder={}, macroBlockSet={}, kitchenMenu={}",
                mealBuilder.getClass().getSimpleName(),
                macroBlockSet.getClass().getSimpleName(),
                kitchenMenu.getClass().getSimpleName());

        return new NutritionModePlan(
                nutritionMode,
                mealBuilder.name(),
                mealBuilder.buildSampleMeals(),
                macroBlockSet.name(),
                macroBlockSet.proteinSources(),
                macroBlockSet.carbSources(),
                kitchenMenu.name(),
                kitchenMenu.availableItems()
        );
    }
}

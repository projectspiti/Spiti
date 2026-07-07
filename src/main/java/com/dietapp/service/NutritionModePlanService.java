package com.dietapp.service;

import com.dietapp.service.nutrition.factory.NutritionModeFactory;
import com.dietapp.model.NutritionMode;
import com.dietapp.model.NutritionModePlan;
import com.dietapp.service.nutrition.product.KitchenMenu;
import com.dietapp.service.nutrition.product.MacroBlockSet;
import com.dietapp.service.nutrition.product.MealBuilder;
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

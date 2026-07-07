package com.dietapp.nutrition.service;

import org.springframework.stereotype.Component;

import com.dietapp.nutrition.factory.NutritionModeFactory;
import com.dietapp.nutrition.model.NutritionMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NutritionModeFactoryResolver {

    private static final Logger log = LoggerFactory.getLogger(NutritionModeFactoryResolver.class);

    private final Map<NutritionMode, NutritionModeFactory> factoriesByMode;

    public NutritionModeFactoryResolver(List<NutritionModeFactory> factories) {
        this.factoriesByMode = new EnumMap<>(NutritionMode.class);
        log.info("Spring injected {} NutritionModeFactory implementations", factories.size());
        for (NutritionModeFactory factory : factories) {
            log.info("registering {} for nutritionMode={}",
                    factory.getClass().getSimpleName(),
                    factory.nutritionMode());
            NutritionModeFactory existingFactory = factoriesByMode.put(factory.nutritionMode(), factory);
            if (existingFactory != null) {
                throw new IllegalStateException("Duplicate nutrition mode factory for " + factory.nutritionMode());
            }
        }
    }

    public NutritionModeFactory resolve(NutritionMode nutritionMode) {
        log.info("resolving nutrition factory for nutritionMode={}", nutritionMode);
        NutritionModeFactory factory = factoriesByMode.get(nutritionMode);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported nutrition mode: " + nutritionMode);
        }
        log.info("selected nutrition factory class={}", factory.getClass().getSimpleName());
        return factory;
    }
}

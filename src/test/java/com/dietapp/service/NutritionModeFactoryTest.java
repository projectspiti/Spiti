package com.dietapp.service.nutrition.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NutritionModeFactoryTest {

    @Test
    void vegFactoryCreatesOnlyVegProductFamily() {
        NutritionModeFactory factory = new VegNutritionModeFactory();

        assertThat(factory.createMealBuilder().name()).isEqualTo("VegMealBuilder");
        assertThat(factory.createMacroBlockSet().name()).isEqualTo("VegMacroBlockSet");
        assertThat(factory.createKitchenMenu().name()).isEqualTo("VegKitchenMenu");
    }

    @Test
    void nonVegFactoryCreatesOnlyNonVegProductFamily() {
        NutritionModeFactory factory = new NonVegNutritionModeFactory();

        assertThat(factory.createMealBuilder().name()).isEqualTo("NonVegMealBuilder");
        assertThat(factory.createMacroBlockSet().name()).isEqualTo("NonVegMacroBlockSet");
        assertThat(factory.createKitchenMenu().name()).isEqualTo("NonVegKitchenMenu");
    }
}

package com.dietapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.service.nutrition.factory.NonVegNutritionModeFactory;
import com.dietapp.service.nutrition.factory.VegNutritionModeFactory;
import com.dietapp.model.NutritionMode;
import com.dietapp.model.NutritionModePlan;
import java.util.List;
import org.junit.jupiter.api.Test;

class NutritionModePlanServiceTest {

    private final NutritionModePlanService service = new NutritionModePlanService(
            new NutritionModeFactoryResolver(List.of(
                    new VegNutritionModeFactory(),
                    new NonVegNutritionModeFactory()
            ))
    );

    @Test
    void createsVegPlanWithVegProductFamily() {
        NutritionModePlan plan = service.createNutritionModePlan(NutritionMode.VEG);

        assertThat(plan.mealBuilderName()).isEqualTo("VegMealBuilder");
        assertThat(plan.macroBlockSetName()).isEqualTo("VegMacroBlockSet");
        assertThat(plan.kitchenMenuName()).isEqualTo("VegKitchenMenu");
        assertThat(plan.proteinSources()).contains("paneer", "tofu");
    }

    @Test
    void createsNonVegPlanWithNonVegProductFamily() {
        NutritionModePlan plan = service.createNutritionModePlan(NutritionMode.NON_VEG);

        assertThat(plan.mealBuilderName()).isEqualTo("NonVegMealBuilder");
        assertThat(plan.macroBlockSetName()).isEqualTo("NonVegMacroBlockSet");
        assertThat(plan.kitchenMenuName()).isEqualTo("NonVegKitchenMenu");
        assertThat(plan.proteinSources()).contains("chicken breast", "eggs");
    }
}

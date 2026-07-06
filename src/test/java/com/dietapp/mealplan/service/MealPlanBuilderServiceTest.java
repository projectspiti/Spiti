package com.dietapp.mealplan.service;

import com.dietapp.mealplan.model.MealPlanSummary;
import com.dietapp.nutrition.model.NutritionMode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MealPlanBuilderServiceTest {

    @Test
    void buildsVegWeightLossPlanUsingBuilder() {
        MealPlanBuilderService service = new MealPlanBuilderService();

        MealPlanSummary summary = service.buildWeightLossPlan(101L, 1600, NutritionMode.VEG, true);

        assertThat(summary.userId()).isEqualTo(101L);
        assertThat(summary.targetCalories()).isEqualTo(1600);
        assertThat(summary.nutritionMode()).isEqualTo(NutritionMode.VEG);
        assertThat(summary.goal()).isEqualTo("WEIGHT_LOSS");
        assertThat(summary.meals()).contains("Paneer protein bowl");
        assertThat(summary.deliveryRequired()).isTrue();
        assertThat(summary.deliverySlot()).isEqualTo("LUNCH_SLOT");
    }

    @Test
    void buildsNonVegWeightLossPlanWithoutDelivery() {
        MealPlanBuilderService service = new MealPlanBuilderService();

        MealPlanSummary summary = service.buildWeightLossPlan(102L, 1900, NutritionMode.NON_VEG, false);

        assertThat(summary.meals()).contains("Grilled chicken protein bowl");
        assertThat(summary.deliveryRequired()).isFalse();
        assertThat(summary.deliverySlot()).isEqualTo("NO_DELIVERY");
    }
}

package com.dietapp.mealplan.model;

import com.dietapp.nutrition.model.NutritionMode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MealPlanTest {

    @Test
    void builderCreatesMealPlanStepByStep() {
        MealPlan mealPlan = MealPlan.builder(101L, 1800, NutritionMode.VEG)
                .goal("WEIGHT_LOSS")
                .addMeal("Paneer bowl")
                .addSnack("Greek yogurt")
                .deliveryRequired(true)
                .deliverySlot("LUNCH_SLOT")
                .notes("No sugar")
                .build();

        assertThat(mealPlan.getUserId()).isEqualTo(101L);
        assertThat(mealPlan.getTargetCalories()).isEqualTo(1800);
        assertThat(mealPlan.getNutritionMode()).isEqualTo(NutritionMode.VEG);
        assertThat(mealPlan.getGoal()).isEqualTo("WEIGHT_LOSS");
        assertThat(mealPlan.getMeals()).containsExactly("Paneer bowl");
        assertThat(mealPlan.getSnacks()).containsExactly("Greek yogurt");
        assertThat(mealPlan.isDeliveryRequired()).isTrue();
        assertThat(mealPlan.getDeliverySlot()).isEqualTo("LUNCH_SLOT");
        assertThat(mealPlan.getNotes()).isEqualTo("No sugar");
    }

    @Test
    void buildFailsWhenMealIsMissing() {
        MealPlan.Builder builder = MealPlan.builder(101L, 1800, NutritionMode.VEG);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("at least one meal is required");
    }

    @Test
    void buildFailsWhenDeliveryRequiredButSlotIsMissing() {
        MealPlan.Builder builder = MealPlan.builder(101L, 1800, NutritionMode.VEG)
                .addMeal("Paneer bowl")
                .deliveryRequired(true);

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("deliverySlot is required when deliveryRequired is true");
    }

    @Test
    void finalMealListsAreImmutable() {
        MealPlan mealPlan = MealPlan.builder(101L, 1800, NutritionMode.VEG)
                .addMeal("Paneer bowl")
                .build();

        assertThatThrownBy(() -> mealPlan.getMeals().add("Changed later"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}

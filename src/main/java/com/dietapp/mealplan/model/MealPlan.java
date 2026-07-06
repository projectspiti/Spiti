package com.dietapp.mealplan.model;

import com.dietapp.nutrition.model.NutritionMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MealPlan {
    private final Long userId;
    private final int targetCalories;
    private final NutritionMode nutritionMode;
    private final String goal;
    private final List<String> meals;
    private final List<String> snacks;
    private final boolean deliveryRequired;
    private final String deliverySlot;
    private final String notes;

    private MealPlan(Builder builder) {
        this.userId = builder.userId;
        this.targetCalories = builder.targetCalories;
        this.nutritionMode = builder.nutritionMode;
        this.goal = builder.goal;
        this.meals = List.copyOf(builder.meals);
        this.snacks = List.copyOf(builder.snacks);
        this.deliveryRequired = builder.deliveryRequired;
        this.deliverySlot = builder.deliverySlot;
        this.notes = builder.notes;
    }

    public static Builder builder(Long userId, int targetCalories, NutritionMode nutritionMode) {
        return new Builder(userId, targetCalories, nutritionMode);
    }

    public Long getUserId() {
        return userId;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public NutritionMode getNutritionMode() {
        return nutritionMode;
    }

    public String getGoal() {
        return goal;
    }

    public List<String> getMeals() {
        return meals;
    }

    public List<String> getSnacks() {
        return snacks;
    }

    public boolean isDeliveryRequired() {
        return deliveryRequired;
    }

    public String getDeliverySlot() {
        return deliverySlot;
    }

    public String getNotes() {
        return notes;
    }

    public static final class Builder {
        private final Long userId;
        private final int targetCalories;
        private final NutritionMode nutritionMode;
        private String goal = "MAINTENANCE";
        private final List<String> meals = new ArrayList<>();
        private final List<String> snacks = new ArrayList<>();
        private boolean deliveryRequired;
        private String deliverySlot = "NO_DELIVERY";
        private String notes = "";

        private Builder(Long userId, int targetCalories, NutritionMode nutritionMode) {
            this.userId = Objects.requireNonNull(userId, "userId is required");
            this.targetCalories = targetCalories;
            this.nutritionMode = Objects.requireNonNull(nutritionMode, "nutritionMode is required");
        }

        public Builder goal(String goal) {
            this.goal = Objects.requireNonNull(goal, "goal is required");
            return this;
        }

        public Builder addMeal(String meal) {
            this.meals.add(Objects.requireNonNull(meal, "meal is required"));
            return this;
        }

        public Builder addSnack(String snack) {
            this.snacks.add(Objects.requireNonNull(snack, "snack is required"));
            return this;
        }

        public Builder deliveryRequired(boolean deliveryRequired) {
            this.deliveryRequired = deliveryRequired;
            return this;
        }

        public Builder deliverySlot(String deliverySlot) {
            this.deliverySlot = Objects.requireNonNull(deliverySlot, "deliverySlot is required");
            return this;
        }

        public Builder notes(String notes) {
            this.notes = Objects.requireNonNull(notes, "notes is required");
            return this;
        }

        public MealPlan build() {
            if (targetCalories <= 0) {
                throw new IllegalStateException("targetCalories must be greater than zero");
            }
            if (meals.isEmpty()) {
                throw new IllegalStateException("at least one meal is required");
            }
            if (deliveryRequired && "NO_DELIVERY".equals(deliverySlot)) {
                throw new IllegalStateException("deliverySlot is required when deliveryRequired is true");
            }
            return new MealPlan(this);
        }
    }
}

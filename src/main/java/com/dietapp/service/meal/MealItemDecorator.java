package com.dietapp.service.meal;

import java.util.List;
import java.util.Objects;

public abstract class MealItemDecorator implements MealItem {
    protected final MealItem mealItem;

    protected MealItemDecorator(MealItem mealItem) {
        this.mealItem = Objects.requireNonNull(mealItem, "mealItem is required");
    }

    @Override
    public String name() {
        return mealItem.name();
    }

    @Override
    public int calories() {
        return mealItem.calories();
    }

    @Override
    public int protein() {
        return mealItem.protein();
    }

    @Override
    public int carbs() {
        return mealItem.carbs();
    }

    @Override
    public int fat() {
        return mealItem.fat();
    }

    @Override
    public int priceInRupees() {
        return mealItem.priceInRupees();
    }

    @Override
    public List<String> appliedAddons() {
        return mealItem.appliedAddons();
    }
}

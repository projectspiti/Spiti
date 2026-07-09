package com.dietapp.service.meal;

import java.util.List;
import java.util.Objects;

public final class BaseMealItem implements MealItem {
    private final String name;
    private final int calories;
    private final int protein;
    private final int carbs;
    private final int fat;
    private final int priceInRupees;

    public BaseMealItem(String name, int calories, int protein, int carbs, int fat, int priceInRupees) {
        this.name = Objects.requireNonNull(name, "name is required");
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.priceInRupees = priceInRupees;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int calories() {
        return calories;
    }

    @Override
    public int protein() {
        return protein;
    }

    @Override
    public int carbs() {
        return carbs;
    }

    @Override
    public int fat() {
        return fat;
    }

    @Override
    public int priceInRupees() {
        return priceInRupees;
    }

    @Override
    public List<String> appliedAddons() {
        return List.of();
    }
}

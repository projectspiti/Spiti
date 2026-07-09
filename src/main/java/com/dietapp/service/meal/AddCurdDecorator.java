package com.dietapp.service.meal;

import java.util.ArrayList;
import java.util.List;

public final class AddCurdDecorator extends MealItemDecorator {
    public AddCurdDecorator(MealItem mealItem) {
        super(mealItem);
    }

    @Override
    public String name() {
        return mealItem.name() + " + Curd";
    }

    @Override
    public int calories() {
        return mealItem.calories() + 60;
    }

    @Override
    public int protein() {
        return mealItem.protein() + 6;
    }

    @Override
    public int carbs() {
        return mealItem.carbs() + 4;
    }

    @Override
    public int fat() {
        return mealItem.fat() + 2;
    }

    @Override
    public int priceInRupees() {
        return mealItem.priceInRupees() + 30;
    }

    @Override
    public List<String> appliedAddons() {
        List<String> addons = new ArrayList<>(mealItem.appliedAddons());
        addons.add("Curd");
        return List.copyOf(addons);
    }
}

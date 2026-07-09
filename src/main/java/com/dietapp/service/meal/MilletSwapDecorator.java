package com.dietapp.service.meal;

import java.util.ArrayList;
import java.util.List;

public final class MilletSwapDecorator extends MealItemDecorator {
    public MilletSwapDecorator(MealItem mealItem) {
        super(mealItem);
    }

    @Override
    public String name() {
        return mealItem.name() + " + Millet Swap";
    }

    @Override
    public int calories() {
        return mealItem.calories() - 30;
    }

    @Override
    public int carbs() {
        return mealItem.carbs() - 8;
    }

    @Override
    public int protein() {
        return mealItem.protein() + 2;
    }

    @Override
    public int priceInRupees() {
        return mealItem.priceInRupees() + 20;
    }

    @Override
    public List<String> appliedAddons() {
        List<String> addons = new ArrayList<>(mealItem.appliedAddons());
        addons.add("Millet Swap");
        return List.copyOf(addons);
    }
}

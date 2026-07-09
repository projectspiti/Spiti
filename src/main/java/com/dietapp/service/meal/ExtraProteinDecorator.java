package com.dietapp.service.meal;

import java.util.ArrayList;
import java.util.List;

public final class ExtraProteinDecorator extends MealItemDecorator {
    public ExtraProteinDecorator(MealItem mealItem) {
        super(mealItem);
    }

    @Override
    public String name() {
        return mealItem.name() + " + Extra Protein";
    }

    @Override
    public int calories() {
        return mealItem.calories() + 110;
    }

    @Override
    public int protein() {
        return mealItem.protein() + 22;
    }

    @Override
    public int fat() {
        return mealItem.fat() + 3;
    }

    @Override
    public int priceInRupees() {
        return mealItem.priceInRupees() + 60;
    }

    @Override
    public List<String> appliedAddons() {
        List<String> addons = new ArrayList<>(mealItem.appliedAddons());
        addons.add("Extra Protein");
        return List.copyOf(addons);
    }
}

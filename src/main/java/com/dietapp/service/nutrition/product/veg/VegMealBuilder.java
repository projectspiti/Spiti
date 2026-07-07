package com.dietapp.service.nutrition.product.veg;

import com.dietapp.service.nutrition.product.MealBuilder;
import java.util.List;

public class VegMealBuilder implements MealBuilder {

    @Override
    public String name() {
        return "VegMealBuilder";
    }

    @Override
    public List<String> buildSampleMeals() {
        return List.of("Paneer bowl with brown rice", "Dal khichdi with curd", "Tofu millet salad");
    }
}

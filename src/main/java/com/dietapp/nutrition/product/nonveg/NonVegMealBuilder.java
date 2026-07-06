package com.dietapp.nutrition.product.nonveg;

import com.dietapp.nutrition.product.MealBuilder;
import java.util.List;

public class NonVegMealBuilder implements MealBuilder {

    @Override
    public String name() {
        return "NonVegMealBuilder";
    }

    @Override
    public List<String> buildSampleMeals() {
        return List.of("Grilled chicken rice bowl", "Egg white breakfast wrap", "Fish curry with red rice");
    }
}

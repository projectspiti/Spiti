package com.dietapp.nutrition.product;

import java.util.List;

public interface MealBuilder {
    String name();

    List<String> buildSampleMeals();
}

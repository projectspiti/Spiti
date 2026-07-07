package com.dietapp.service.nutrition.product;

import java.util.List;

public interface MealBuilder {
    String name();

    List<String> buildSampleMeals();
}

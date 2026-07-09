package com.dietapp.service.meal;

import java.util.List;

public interface MealItem {
    String name();

    int calories();

    int protein();

    int carbs();

    int fat();

    int priceInRupees();

    List<String> appliedAddons();
}

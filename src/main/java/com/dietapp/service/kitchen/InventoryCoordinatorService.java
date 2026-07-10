package com.dietapp.service.kitchen;

import org.springframework.stereotype.Component;

@Component
public class InventoryCoordinatorService {

    public String reserveIngredients(String mealName) {
        return "Ingredients reserved for " + mealName;
    }
}

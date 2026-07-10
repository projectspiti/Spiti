package com.dietapp.service.kitchen;

import org.springframework.stereotype.Component;

@Component
public class KitchenStationCoordinatorService {

    public String assignStation(String mealName, boolean priorityOrder) {
        if (priorityOrder) {
            return "Priority kitchen station";
        }
        if (mealName.toLowerCase().contains("paneer") || mealName.toLowerCase().contains("tofu")) {
            return "Vegetarian hot station";
        }
        return "General kitchen station";
    }
}

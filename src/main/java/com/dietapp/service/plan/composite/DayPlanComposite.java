package com.dietapp.service.plan.composite;

import java.util.List;

public class DayPlanComposite implements PlanComponent {

    private final String name;
    private final List<PlanComponent> meals;

    public DayPlanComposite(String name, List<PlanComponent> meals) {
        this.name = name;
        this.meals = List.copyOf(meals);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int totalCalories() {
        return meals.stream()
                .mapToInt(PlanComponent::totalCalories)
                .sum();
    }

    @Override
    public int totalPriceInRupees() {
        return meals.stream()
                .mapToInt(PlanComponent::totalPriceInRupees)
                .sum();
    }

    public List<PlanComponent> meals() {
        return meals;
    }
}

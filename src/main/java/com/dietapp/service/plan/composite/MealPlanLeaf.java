package com.dietapp.service.plan.composite;

public class MealPlanLeaf implements PlanComponent {

    private final String name;
    private final int calories;
    private final int priceInRupees;

    public MealPlanLeaf(String name, int calories, int priceInRupees) {
        this.name = name;
        this.calories = calories;
        this.priceInRupees = priceInRupees;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int totalCalories() {
        return calories;
    }

    @Override
    public int totalPriceInRupees() {
        return priceInRupees;
    }
}

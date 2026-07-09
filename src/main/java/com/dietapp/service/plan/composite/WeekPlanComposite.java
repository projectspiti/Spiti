package com.dietapp.service.plan.composite;

import java.util.List;

public class WeekPlanComposite implements PlanComponent {

    private final String name;
    private final List<DayPlanComposite> days;

    public WeekPlanComposite(String name, List<DayPlanComposite> days) {
        this.name = name;
        this.days = List.copyOf(days);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int totalCalories() {
        return days.stream()
                .mapToInt(PlanComponent::totalCalories)
                .sum();
    }

    @Override
    public int totalPriceInRupees() {
        return days.stream()
                .mapToInt(PlanComponent::totalPriceInRupees)
                .sum();
    }

    public List<DayPlanComposite> days() {
        return days;
    }
}

package com.dietapp.service.order.visitor;

import java.util.ArrayList;
import java.util.List;

public class NutritionReportVisitor implements OrderVisitor {

    private final List<String> lines = new ArrayList<>();
    private int totalCalories;
    private int totalProtein;

    @Override
    public void visit(OrderReportOrder order) {
        lines.add("Nutrition report for order " + order.orderId());
    }

    @Override
    public void visit(OrderReportMeal meal) {
        totalCalories += meal.calories();
        totalProtein += meal.protein();
        lines.add(meal.mealName() + ": " + meal.calories() + " calories, " + meal.protein() + "g protein");
    }

    public List<String> lines() {
        return List.copyOf(lines);
    }

    public int totalCalories() {
        return totalCalories;
    }

    public int totalProtein() {
        return totalProtein;
    }
}

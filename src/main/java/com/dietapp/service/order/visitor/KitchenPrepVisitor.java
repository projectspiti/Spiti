package com.dietapp.service.order.visitor;

import java.util.ArrayList;
import java.util.List;

public class KitchenPrepVisitor implements OrderVisitor {

    private final List<String> lines = new ArrayList<>();

    @Override
    public void visit(OrderReportOrder order) {
        lines.add("Prepare kitchen checklist for order " + order.orderId());
    }

    @Override
    public void visit(OrderReportMeal meal) {
        lines.add("Prep meal: " + meal.mealName());
    }

    public List<String> lines() {
        return List.copyOf(lines);
    }
}

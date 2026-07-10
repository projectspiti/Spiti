package com.dietapp.service.order.visitor;

import java.util.List;

public record OrderReportOrder(String orderId, List<OrderReportMeal> meals) implements OrderVisitable {

    @Override
    public void accept(OrderVisitor visitor) {
        visitor.visit(this);
        meals.forEach(meal -> meal.accept(visitor));
    }
}

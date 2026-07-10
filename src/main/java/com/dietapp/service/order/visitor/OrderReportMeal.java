package com.dietapp.service.order.visitor;

public record OrderReportMeal(
        String mealName,
        int calories,
        int protein,
        int priceInRupees
) implements OrderVisitable {

    @Override
    public void accept(OrderVisitor visitor) {
        visitor.visit(this);
    }
}

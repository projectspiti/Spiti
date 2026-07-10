package com.dietapp.service.order.visitor;

public interface OrderVisitor {

    void visit(OrderReportOrder order);

    void visit(OrderReportMeal meal);
}

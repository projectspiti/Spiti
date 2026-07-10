package com.dietapp.service.order.visitor;

import java.util.ArrayList;
import java.util.List;

public class InvoiceVisitor implements OrderVisitor {

    private final List<String> lines = new ArrayList<>();
    private int totalPriceInRupees;

    @Override
    public void visit(OrderReportOrder order) {
        lines.add("Invoice for order " + order.orderId());
    }

    @Override
    public void visit(OrderReportMeal meal) {
        totalPriceInRupees += meal.priceInRupees();
        lines.add(meal.mealName() + ": Rs " + meal.priceInRupees());
    }

    public List<String> lines() {
        List<String> invoiceLines = new ArrayList<>(lines);
        invoiceLines.add("Total: Rs " + totalPriceInRupees);
        return List.copyOf(invoiceLines);
    }

    public int totalPriceInRupees() {
        return totalPriceInRupees;
    }
}

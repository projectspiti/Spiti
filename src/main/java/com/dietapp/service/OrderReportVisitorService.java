package com.dietapp.service;

import com.dietapp.model.OrderReportMealRequest;
import com.dietapp.model.OrderReportRequest;
import com.dietapp.model.OrderReportResponse;
import com.dietapp.model.OrderReportType;
import com.dietapp.service.order.visitor.InvoiceVisitor;
import com.dietapp.service.order.visitor.KitchenPrepVisitor;
import com.dietapp.service.order.visitor.NutritionReportVisitor;
import com.dietapp.service.order.visitor.OrderReportMeal;
import com.dietapp.service.order.visitor.OrderReportOrder;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderReportVisitorService {

    public OrderReportResponse generate(OrderReportRequest request) {
        validate(request);
        OrderReportOrder order = new OrderReportOrder(
                request.orderId(),
                request.meals().stream().map(this::toMeal).toList()
        );

        return switch (request.reportType()) {
            case KITCHEN_PREP -> kitchenPrep(order);
            case INVOICE -> invoice(order);
            case NUTRITION -> nutrition(order);
        };
    }

    private OrderReportResponse kitchenPrep(OrderReportOrder order) {
        KitchenPrepVisitor visitor = new KitchenPrepVisitor();
        order.accept(visitor);
        return new OrderReportResponse(order.orderId(), OrderReportType.KITCHEN_PREP, visitor.lines(), 0, 0, 0);
    }

    private OrderReportResponse invoice(OrderReportOrder order) {
        InvoiceVisitor visitor = new InvoiceVisitor();
        order.accept(visitor);
        return new OrderReportResponse(order.orderId(), OrderReportType.INVOICE, visitor.lines(), 0, 0, visitor.totalPriceInRupees());
    }

    private OrderReportResponse nutrition(OrderReportOrder order) {
        NutritionReportVisitor visitor = new NutritionReportVisitor();
        order.accept(visitor);
        return new OrderReportResponse(order.orderId(), OrderReportType.NUTRITION, visitor.lines(), visitor.totalCalories(), visitor.totalProtein(), 0);
    }

    private OrderReportMeal toMeal(OrderReportMealRequest request) {
        return new OrderReportMeal(request.mealName(), request.calories(), request.protein(), request.priceInRupees());
    }

    private void validate(OrderReportRequest request) {
        if (request.orderId() == null || request.orderId().isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.reportType() == null) {
            throw new IllegalArgumentException("reportType is required");
        }
        if (request.meals() == null || request.meals().isEmpty()) {
            throw new IllegalArgumentException("meals are required");
        }
    }
}

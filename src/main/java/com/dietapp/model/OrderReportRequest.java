package com.dietapp.model;

import java.util.List;

public record OrderReportRequest(
        String orderId,
        OrderReportType reportType,
        List<OrderReportMealRequest> meals
) {
}

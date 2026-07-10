package com.dietapp.model;

import java.util.List;

public record OrderReportResponse(
        String orderId,
        OrderReportType reportType,
        List<String> lines,
        int totalCalories,
        int totalProtein,
        int totalPriceInRupees
) {
}

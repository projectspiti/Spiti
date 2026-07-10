package com.dietapp.service;

import com.dietapp.model.OrderReportMealRequest;
import com.dietapp.model.OrderReportRequest;
import com.dietapp.model.OrderReportResponse;
import com.dietapp.model.OrderReportType;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderReportVisitorServiceTest {

    @Test
    void generatesNutritionReportWithoutChangingOrderObjects() {
        OrderReportVisitorService service = new OrderReportVisitorService();

        OrderReportResponse response = service.generate(new OrderReportRequest(
                "ORDER-301",
                OrderReportType.NUTRITION,
                List.of(
                        new OrderReportMealRequest("Tofu Bowl", 420, 34, 180),
                        new OrderReportMealRequest("Sprout Salad", 310, 22, 140)
                )
        ));

        assertThat(response.totalCalories()).isEqualTo(730);
        assertThat(response.totalProtein()).isEqualTo(56);
        assertThat(response.lines()).contains("Tofu Bowl: 420 calories, 34g protein");
    }
}

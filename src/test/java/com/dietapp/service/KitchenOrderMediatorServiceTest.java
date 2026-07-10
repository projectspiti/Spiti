package com.dietapp.service;

import com.dietapp.model.DayType;
import com.dietapp.model.KitchenOrderRequest;
import com.dietapp.model.KitchenOrderResponse;
import com.dietapp.service.kitchen.DeliveryCoordinatorService;
import com.dietapp.service.kitchen.InventoryCoordinatorService;
import com.dietapp.service.kitchen.KitchenNotificationCoordinatorService;
import com.dietapp.service.kitchen.KitchenStationCoordinatorService;
import com.dietapp.service.kitchen.PackagingCoordinatorService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KitchenOrderMediatorServiceTest {

    @Test
    void coordinatesKitchenOrderAcrossMultipleServices() {
        KitchenOrderMediatorService service = new KitchenOrderMediatorService(
                new InventoryCoordinatorService(),
                new KitchenStationCoordinatorService(),
                new PackagingCoordinatorService(),
                new DeliveryCoordinatorService(),
                new KitchenNotificationCoordinatorService()
        );

        KitchenOrderResponse response = service.coordinate(new KitchenOrderRequest(
                "ORDER-401",
                "Paneer Bowl",
                DayType.OFFICE,
                false
        ));

        assertThat(response.station()).isEqualTo("Vegetarian hot station");
        assertThat(response.packaging()).isEqualTo("Leak-proof office packaging");
        assertThat(response.coordinationSteps()).hasSize(5);
    }
}

package com.dietapp.service;

import com.dietapp.model.KitchenOrderRequest;
import com.dietapp.model.KitchenOrderResponse;
import com.dietapp.service.kitchen.DeliveryCoordinatorService;
import com.dietapp.service.kitchen.InventoryCoordinatorService;
import com.dietapp.service.kitchen.KitchenNotificationCoordinatorService;
import com.dietapp.service.kitchen.KitchenStationCoordinatorService;
import com.dietapp.service.kitchen.PackagingCoordinatorService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KitchenOrderMediatorService {

    private static final Logger log = LoggerFactory.getLogger(KitchenOrderMediatorService.class);

    private final InventoryCoordinatorService inventoryCoordinatorService;
    private final KitchenStationCoordinatorService kitchenStationCoordinatorService;
    private final PackagingCoordinatorService packagingCoordinatorService;
    private final DeliveryCoordinatorService deliveryCoordinatorService;
    private final KitchenNotificationCoordinatorService kitchenNotificationCoordinatorService;

    public KitchenOrderMediatorService(
            InventoryCoordinatorService inventoryCoordinatorService,
            KitchenStationCoordinatorService kitchenStationCoordinatorService,
            PackagingCoordinatorService packagingCoordinatorService,
            DeliveryCoordinatorService deliveryCoordinatorService,
            KitchenNotificationCoordinatorService kitchenNotificationCoordinatorService
    ) {
        this.inventoryCoordinatorService = inventoryCoordinatorService;
        this.kitchenStationCoordinatorService = kitchenStationCoordinatorService;
        this.packagingCoordinatorService = packagingCoordinatorService;
        this.deliveryCoordinatorService = deliveryCoordinatorService;
        this.kitchenNotificationCoordinatorService = kitchenNotificationCoordinatorService;
    }

    public KitchenOrderResponse coordinate(KitchenOrderRequest request) {
        validate(request);
        log.info("Coordinating kitchen order orderId={}, mealName={}, dayType={}",
                request.orderId(), request.mealName(), request.dayType());

        List<String> steps = new ArrayList<>();
        steps.add(inventoryCoordinatorService.reserveIngredients(request.mealName()));
        String station = kitchenStationCoordinatorService.assignStation(request.mealName(), request.priorityOrder());
        steps.add("Station assigned: " + station);
        String packaging = packagingCoordinatorService.selectPackaging(request.dayType());
        steps.add("Packaging selected: " + packaging);
        String deliverySlot = deliveryCoordinatorService.schedulePickup(request.dayType());
        steps.add("Delivery scheduled: " + deliverySlot);
        steps.add(kitchenNotificationCoordinatorService.notifyTeams(request.orderId()));

        return new KitchenOrderResponse(request.orderId(), station, packaging, deliverySlot, steps);
    }

    private void validate(KitchenOrderRequest request) {
        if (request.orderId() == null || request.orderId().isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.mealName() == null || request.mealName().isBlank()) {
            throw new IllegalArgumentException("mealName is required");
        }
        if (request.dayType() == null) {
            throw new IllegalArgumentException("dayType is required");
        }
    }
}

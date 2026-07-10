package com.dietapp.controller;

import com.dietapp.model.KitchenOrderRequest;
import com.dietapp.model.KitchenOrderResponse;
import com.dietapp.service.KitchenOrderMediatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenOrderMediatorController {

    private final KitchenOrderMediatorService kitchenOrderMediatorService;

    public KitchenOrderMediatorController(KitchenOrderMediatorService kitchenOrderMediatorService) {
        this.kitchenOrderMediatorService = kitchenOrderMediatorService;
    }

    @PostMapping("/orders/coordinate")
    public KitchenOrderResponse coordinate(@RequestBody KitchenOrderRequest request) {
        return kitchenOrderMediatorService.coordinate(request);
    }
}

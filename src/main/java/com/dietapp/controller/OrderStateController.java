package com.dietapp.controller;

import com.dietapp.model.OrderStateTransitionResponse;
import com.dietapp.model.OrderStatus;
import com.dietapp.service.OrderStateMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders/{orderId}/state")
public class OrderStateController {

    private final OrderStateMachineService orderStateMachineService;

    public OrderStateController(OrderStateMachineService orderStateMachineService) {
        this.orderStateMachineService = orderStateMachineService;
    }

    @PostMapping("/confirm")
    public OrderStateTransitionResponse confirm(@PathVariable String orderId) {
        return orderStateMachineService.confirmOrder(orderId);
    }

    @PostMapping("/prepare")
    public OrderStateTransitionResponse prepare(@PathVariable String orderId) {
        return orderStateMachineService.startPreparing(orderId);
    }

    @PostMapping("/out-for-delivery")
    public OrderStateTransitionResponse outForDelivery(@PathVariable String orderId) {
        return orderStateMachineService.markOutForDelivery(orderId);
    }

    @PostMapping("/deliver")
    public OrderStateTransitionResponse deliver(@PathVariable String orderId) {
        return orderStateMachineService.markDelivered(orderId);
    }

    @GetMapping
    public OrderStatus currentStatus(@PathVariable String orderId) {
        return orderStateMachineService.currentStatus(orderId);
    }
}

package com.dietapp.controller;

import com.dietapp.model.OrderReportRequest;
import com.dietapp.model.OrderReportResponse;
import com.dietapp.service.OrderReportVisitorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order-reports")
public class OrderReportController {

    private final OrderReportVisitorService orderReportVisitorService;

    public OrderReportController(OrderReportVisitorService orderReportVisitorService) {
        this.orderReportVisitorService = orderReportVisitorService;
    }

    @PostMapping("/generate")
    public OrderReportResponse generate(@RequestBody OrderReportRequest request) {
        return orderReportVisitorService.generate(request);
    }
}

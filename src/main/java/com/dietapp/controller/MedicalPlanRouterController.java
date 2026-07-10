package com.dietapp.controller;

import com.dietapp.model.MedicalPlanRouteRequest;
import com.dietapp.model.MedicalPlanRouteResponse;
import com.dietapp.service.MedicalPlanRouterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medical-plan-router")
public class MedicalPlanRouterController {

    private final MedicalPlanRouterService medicalPlanRouterService;

    public MedicalPlanRouterController(MedicalPlanRouterService medicalPlanRouterService) {
        this.medicalPlanRouterService = medicalPlanRouterService;
    }

    @PostMapping("/route")
    public MedicalPlanRouteResponse route(@RequestBody MedicalPlanRouteRequest request) {
        return medicalPlanRouterService.route(request);
    }
}

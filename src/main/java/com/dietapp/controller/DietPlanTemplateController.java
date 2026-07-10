package com.dietapp.controller;

import com.dietapp.model.DietPlanTemplateRequest;
import com.dietapp.model.DietPlanTemplateResponse;
import com.dietapp.service.DietPlanTemplateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diet-plan-templates")
public class DietPlanTemplateController {

    private final DietPlanTemplateService dietPlanTemplateService;

    public DietPlanTemplateController(DietPlanTemplateService dietPlanTemplateService) {
        this.dietPlanTemplateService = dietPlanTemplateService;
    }

    @PostMapping("/create")
    public DietPlanTemplateResponse create(@RequestBody DietPlanTemplateRequest request) {
        return dietPlanTemplateService.createPlan(request);
    }
}

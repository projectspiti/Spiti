package com.dietapp.nutrition.controller;

import com.dietapp.nutrition.model.NutritionModePlan;
import com.dietapp.nutrition.service.NutritionModePlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nutrition-modes")
public class NutritionModeController {

    private final NutritionModePlanService nutritionModePlanService;

    public NutritionModeController(NutritionModePlanService nutritionModePlanService) {
        this.nutritionModePlanService = nutritionModePlanService;
    }

    @PostMapping("/plan")
    public NutritionModePlanResponse createNutritionModePlan(@RequestBody NutritionModePlanRequest request) {
        NutritionModePlan plan = nutritionModePlanService.createNutritionModePlan(request.nutritionMode());
        return NutritionModePlanResponse.from(plan);
    }
}

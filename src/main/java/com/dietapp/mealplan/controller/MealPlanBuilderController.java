package com.dietapp.mealplan.controller;

import com.dietapp.mealplan.model.MealPlanSummary;
import com.dietapp.mealplan.service.MealPlanBuilderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanBuilderController {
    private final MealPlanBuilderService mealPlanBuilderService;

    public MealPlanBuilderController(MealPlanBuilderService mealPlanBuilderService) {
        this.mealPlanBuilderService = mealPlanBuilderService;
    }

    @PostMapping("/builder")
    public MealPlanSummary buildMealPlan(@RequestBody MealPlanBuilderRequest request) {
        return mealPlanBuilderService.buildWeightLossPlan(
                request.userId(),
                request.targetCalories(),
                request.nutritionMode(),
                request.deliveryRequired()
        );
    }
}

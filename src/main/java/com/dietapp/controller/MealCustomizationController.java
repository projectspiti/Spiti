package com.dietapp.controller;

import com.dietapp.model.MealCustomizationRequest;
import com.dietapp.model.MealCustomizationResponse;
import com.dietapp.service.MealCustomizationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meals")
public class MealCustomizationController {
    private final MealCustomizationService mealCustomizationService;

    public MealCustomizationController(MealCustomizationService mealCustomizationService) {
        this.mealCustomizationService = mealCustomizationService;
    }

    @PostMapping("/customize")
    public MealCustomizationResponse customize(@RequestBody MealCustomizationRequest request) {
        return mealCustomizationService.customize(request);
    }
}

package com.dietapp.controller;

import com.dietapp.model.MealSwapSuggestionRequest;
import com.dietapp.model.MealSwapSuggestionResponse;
import com.dietapp.service.MealSwapIteratorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meal-swaps")
public class MealSwapIteratorController {

    private final MealSwapIteratorService mealSwapIteratorService;

    public MealSwapIteratorController(MealSwapIteratorService mealSwapIteratorService) {
        this.mealSwapIteratorService = mealSwapIteratorService;
    }

    @PostMapping("/suggestions")
    public MealSwapSuggestionResponse suggestions(@RequestBody MealSwapSuggestionRequest request) {
        return mealSwapIteratorService.suggestions(request);
    }
}

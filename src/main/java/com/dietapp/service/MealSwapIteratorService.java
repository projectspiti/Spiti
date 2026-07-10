package com.dietapp.service;

import com.dietapp.model.MealSwapSuggestion;
import com.dietapp.model.MealSwapSuggestionRequest;
import com.dietapp.model.MealSwapSuggestionResponse;
import com.dietapp.service.meal.iterator.MealIterator;
import com.dietapp.service.meal.iterator.MealSwapSuggestionCollection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MealSwapIteratorService {

    private final MealSwapSuggestionCollection collection = new MealSwapSuggestionCollection();

    public MealSwapSuggestionResponse suggestions(MealSwapSuggestionRequest request) {
        validate(request);
        MealIterator iterator = collection.iterator(request);
        List<MealSwapSuggestion> suggestions = new ArrayList<>();
        while (iterator.hasNext()) {
            suggestions.add(iterator.next());
        }
        return new MealSwapSuggestionResponse(request.currentMealName(), suggestions);
    }

    private void validate(MealSwapSuggestionRequest request) {
        if (request.currentMealName() == null || request.currentMealName().isBlank()) {
            throw new IllegalArgumentException("currentMealName is required");
        }
        if (request.nutritionMode() == null) {
            throw new IllegalArgumentException("nutritionMode is required");
        }
        if (request.maxCalories() <= 0) {
            throw new IllegalArgumentException("maxCalories must be positive");
        }
        if (request.minimumProtein() < 0) {
            throw new IllegalArgumentException("minimumProtein cannot be negative");
        }
    }
}

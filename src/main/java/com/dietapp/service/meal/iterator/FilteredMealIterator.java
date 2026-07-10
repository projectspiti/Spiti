package com.dietapp.service.meal.iterator;

import com.dietapp.model.MealSwapSuggestion;
import com.dietapp.model.MealSwapSuggestionRequest;
import java.util.List;
import java.util.NoSuchElementException;

public class FilteredMealIterator implements MealIterator {

    private final List<MealSwapSuggestion> meals;
    private final MealSwapSuggestionRequest request;
    private int position;

    public FilteredMealIterator(List<MealSwapSuggestion> meals, MealSwapSuggestionRequest request) {
        this.meals = meals;
        this.request = request;
    }

    @Override
    public boolean hasNext() {
        while (position < meals.size()) {
            if (matches(meals.get(position))) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public MealSwapSuggestion next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more meal swap suggestions");
        }
        return meals.get(position++);
    }

    private boolean matches(MealSwapSuggestion meal) {
        return !meal.mealName().equalsIgnoreCase(request.currentMealName())
                && meal.nutritionMode() == request.nutritionMode()
                && meal.calories() <= request.maxCalories()
                && meal.protein() >= request.minimumProtein();
    }
}

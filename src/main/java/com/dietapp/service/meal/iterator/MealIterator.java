package com.dietapp.service.meal.iterator;

import com.dietapp.model.MealSwapSuggestion;

public interface MealIterator {

    boolean hasNext();

    MealSwapSuggestion next();
}

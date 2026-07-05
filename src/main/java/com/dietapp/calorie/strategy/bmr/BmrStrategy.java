package com.dietapp.calorie.strategy.bmr;

import com.dietapp.calorie.model.UserProfile;

public interface BmrStrategy {

    String name();

    int priority();

    boolean supports(UserProfile userProfile);

    double calculateBmr(UserProfile userProfile);
}

package com.dietapp.calorie.model;

public enum ActivityLevel {
    SEDENTARY(1.20),
    LIGHT(1.375),
    MODERATE(1.55),
    ACTIVE(1.725);

    private final double multiplier;

    ActivityLevel(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}

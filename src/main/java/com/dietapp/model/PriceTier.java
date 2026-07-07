package com.dietapp.model;

public enum PriceTier {
    BUDGET(100),
    STANDARD(200),
    PERFORMANCE(300),
    PREMIUM(450);

    private final int dailyBudgetInRupees;

    PriceTier(int dailyBudgetInRupees) {
        this.dailyBudgetInRupees = dailyBudgetInRupees;
    }

    public int getDailyBudgetInRupees() {
        return dailyBudgetInRupees;
    }
}

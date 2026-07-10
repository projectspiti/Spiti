package com.dietapp.service.cart;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final String cartId;
    private final List<String> meals = new ArrayList<>();
    private String couponCode;

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String cartId() {
        return cartId;
    }

    public void addMeal(String mealName) {
        meals.add(mealName);
    }

    public void removeMeal(String mealName) {
        if (!meals.remove(mealName)) {
            throw new IllegalArgumentException("Meal not found in cart: " + mealName);
        }
    }

    public void swapMeal(String mealName, String replacementMealName) {
        int index = meals.indexOf(mealName);
        if (index < 0) {
            throw new IllegalArgumentException("Meal not found in cart: " + mealName);
        }
        meals.set(index, replacementMealName);
    }

    public void applyCoupon(String couponCode) {
        this.couponCode = couponCode;
    }

    public void restoreCoupon(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<String> meals() {
        return List.copyOf(meals);
    }

    public String couponCode() {
        return couponCode;
    }
}

package com.dietapp.kitchen.controller;

public record DishTemplateDuplicateRequest(
        String sourceTemplateCode,
        String newTemplateCode,
        String newDishName,
        int newBasePriceInRupees
) {
}

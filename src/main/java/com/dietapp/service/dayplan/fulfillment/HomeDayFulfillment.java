package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;

public class HomeDayFulfillment implements DayFulfillment {

    @Override
    public DayType dayType() {
        return DayType.HOME;
    }

    @Override
    public String mealRule() {
        return "Fresh home-style meals with more elaborate options";
    }

    @Override
    public String packagingRule() {
        return "Standard hot-meal packaging";
    }

    @Override
    public String deliveryRule() {
        return "Use a flexible home delivery slot";
    }
}

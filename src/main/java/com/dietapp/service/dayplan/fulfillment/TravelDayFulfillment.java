package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;

public class TravelDayFulfillment implements DayFulfillment {

    @Override
    public DayType dayType() {
        return DayType.TRAVEL;
    }

    @Override
    public String mealRule() {
        return "Portable shelf-stable meals that are easy to carry";
    }

    @Override
    public String packagingRule() {
        return "Compact travel packaging with minimal spill risk";
    }

    @Override
    public String deliveryRule() {
        return "Deliver before the travel departure window";
    }
}

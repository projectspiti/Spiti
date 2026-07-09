package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;

public class OfficeDayFulfillment implements DayFulfillment {

    @Override
    public DayType dayType() {
        return DayType.OFFICE;
    }

    @Override
    public String mealRule() {
        return "Non-soggy office meals with controlled portions";
    }

    @Override
    public String packagingRule() {
        return "Leak-proof packaging with separate compartments";
    }

    @Override
    public String deliveryRule() {
        return "Deliver before the office lunch cutoff";
    }
}

package com.dietapp.dayplan.fulfillment;

import com.dietapp.dayplan.model.DayType;

public interface DayFulfillment {

    DayType dayType();

    String mealRule();

    String packagingRule();

    String deliveryRule();
}

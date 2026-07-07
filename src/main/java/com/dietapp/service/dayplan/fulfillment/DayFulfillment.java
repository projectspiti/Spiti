package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;

public interface DayFulfillment {

    DayType dayType();

    String mealRule();

    String packagingRule();

    String deliveryRule();
}

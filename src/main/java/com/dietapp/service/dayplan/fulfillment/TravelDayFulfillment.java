package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TravelDayFulfillment implements DayFulfillment {

    private static final Logger log = LoggerFactory.getLogger(TravelDayFulfillment.class);

    @Override
    public DayType dayType() {
        log.info("TravelDayFulfillment.dayType() called");
        return DayType.TRAVEL;
    }

    @Override
    public String mealRule() {
        log.info("TravelDayFulfillment.mealRule() called");
        return "Portable shelf-stable meals that are easy to carry";
    }

    @Override
    public String packagingRule() {
        log.info("TravelDayFulfillment.packagingRule() called");
        return "Compact travel packaging with minimal spill risk";
    }

    @Override
    public String deliveryRule() {
        log.info("TravelDayFulfillment.deliveryRule() called");
        return "Deliver before the travel departure window";
    }
}

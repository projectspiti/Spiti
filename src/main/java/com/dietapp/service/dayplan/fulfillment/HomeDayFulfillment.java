package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeDayFulfillment implements DayFulfillment {

    private static final Logger log = LoggerFactory.getLogger(HomeDayFulfillment.class);

    @Override
    public DayType dayType() {
        log.info("HomeDayFulfillment.dayType() called");
        return DayType.HOME;
    }

    @Override
    public String mealRule() {
        log.info("HomeDayFulfillment.mealRule() called");
        return "Fresh home-style meals with more elaborate options";
    }

    @Override
    public String packagingRule() {
        log.info("HomeDayFulfillment.packagingRule() called");
        return "Standard hot-meal packaging";
    }

    @Override
    public String deliveryRule() {
        log.info("HomeDayFulfillment.deliveryRule() called");
        return "Use a flexible home delivery slot";
    }
}

package com.dietapp.service.dayplan.fulfillment;

import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfficeDayFulfillment implements DayFulfillment {

    private static final Logger log = LoggerFactory.getLogger(OfficeDayFulfillment.class);

    @Override
    public DayType dayType() {
        log.info("OfficeDayFulfillment.dayType() called");
        return DayType.OFFICE;
    }

    @Override
    public String mealRule() {
        log.info("OfficeDayFulfillment.mealRule() called");
        return "Non-soggy office meals with controlled portions";
    }

    @Override
    public String packagingRule() {
        log.info("OfficeDayFulfillment.packagingRule() called");
        return "Leak-proof packaging with separate compartments";
    }

    @Override
    public String deliveryRule() {
        log.info("OfficeDayFulfillment.deliveryRule() called");
        return "Deliver before the office lunch cutoff";
    }
}

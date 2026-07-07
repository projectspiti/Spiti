package com.dietapp.service.dayplan.creator;

import org.springframework.stereotype.Component;

import com.dietapp.service.dayplan.fulfillment.DayFulfillment;
import com.dietapp.service.dayplan.fulfillment.TravelDayFulfillment;
import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TravelDayPlanCreator extends DayPlanCreator {

    private static final Logger log = LoggerFactory.getLogger(TravelDayPlanCreator.class);

    @Override
    public DayType dayType() {
        return DayType.TRAVEL;
    }

    @Override
    protected DayFulfillment createFulfillment() {
        log.info("TravelDayPlanCreator.createFulfillment() called; creating TravelDayFulfillment");
        return new TravelDayFulfillment();
    }
}

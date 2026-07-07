package com.dietapp.service.dayplan.creator;

import org.springframework.stereotype.Component;

import com.dietapp.service.dayplan.fulfillment.DayFulfillment;
import com.dietapp.service.dayplan.fulfillment.HomeDayFulfillment;
import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class HomeDayPlanCreator extends DayPlanCreator {

    private static final Logger log = LoggerFactory.getLogger(HomeDayPlanCreator.class);

    @Override
    public DayType dayType() {
        return DayType.HOME;
    }

    @Override
    protected DayFulfillment createFulfillment() {
        log.info("HomeDayPlanCreator.createFulfillment() called; creating HomeDayFulfillment");
        return new HomeDayFulfillment();
    }
}

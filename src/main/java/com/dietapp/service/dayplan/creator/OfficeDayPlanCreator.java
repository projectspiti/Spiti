package com.dietapp.service.dayplan.creator;

import org.springframework.stereotype.Component;

import com.dietapp.service.dayplan.fulfillment.DayFulfillment;
import com.dietapp.service.dayplan.fulfillment.OfficeDayFulfillment;
import com.dietapp.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OfficeDayPlanCreator extends DayPlanCreator {

    private static final Logger log = LoggerFactory.getLogger(OfficeDayPlanCreator.class);

    @Override
    public DayType dayType() {
        return DayType.OFFICE;
    }

    @Override
    protected DayFulfillment createFulfillment() {
        log.info("OfficeDayPlanCreator.createFulfillment() called; creating OfficeDayFulfillment");
        return new OfficeDayFulfillment();
    }
}

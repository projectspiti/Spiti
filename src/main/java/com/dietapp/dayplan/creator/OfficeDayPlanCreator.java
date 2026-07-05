package com.dietapp.dayplan.creator;

import com.dietapp.dayplan.fulfillment.DayFulfillment;
import com.dietapp.dayplan.fulfillment.OfficeDayFulfillment;
import com.dietapp.dayplan.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

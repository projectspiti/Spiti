package com.dietapp.dayplan.creator;

import com.dietapp.dayplan.fulfillment.DayFulfillment;
import com.dietapp.dayplan.fulfillment.HomeDayFulfillment;
import com.dietapp.dayplan.model.DayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

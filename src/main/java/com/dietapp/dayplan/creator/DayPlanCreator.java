package com.dietapp.dayplan.creator;

import com.dietapp.dayplan.fulfillment.DayFulfillment;
import com.dietapp.dayplan.model.DayPlan;
import com.dietapp.dayplan.model.DayType;
import com.dietapp.dayplan.model.PriceTier;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DayPlanCreator {

    private static final Logger log = LoggerFactory.getLogger(DayPlanCreator.class);

    public abstract DayType dayType();

    public final DayPlan createPlan(LocalDate date, PriceTier priceTier) {
        log.info("entered parent DayPlanCreator.createPlan using runtime creator={}",
                getClass().getSimpleName());
        if (date == null) {
            throw new IllegalArgumentException("date is required");
        }
        if (priceTier == null) {
            throw new IllegalArgumentException("priceTier is required");
        }

        log.info("parent is about to call abstract createFulfillment(); subclass will decide object");
        DayFulfillment fulfillment = createFulfillment();
        log.info("subclass returned concrete fulfillment={}",
                fulfillment.getClass().getSimpleName());
        log.info("building DayPlan from fulfillment rules: dayType={}, mealRule={}, packagingRule={}, deliveryRule={}",
                fulfillment.dayType(),
                fulfillment.mealRule(),
                fulfillment.packagingRule(),
                fulfillment.deliveryRule());
        return new DayPlan(
                date,
                fulfillment.dayType(),
                priceTier,
                priceTier.getDailyBudgetInRupees(),
                fulfillment.mealRule(),
                fulfillment.packagingRule(),
                fulfillment.deliveryRule()
        );
    }

    protected abstract DayFulfillment createFulfillment();
}

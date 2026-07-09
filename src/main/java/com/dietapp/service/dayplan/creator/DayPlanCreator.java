package com.dietapp.service.dayplan.creator;

import com.dietapp.service.dayplan.fulfillment.DayFulfillment;
import com.dietapp.model.DayPlan;
import com.dietapp.model.DayType;
import com.dietapp.model.PriceTier;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DayPlanCreator {

    private static final Logger log = LoggerFactory.getLogger(DayPlanCreator.class);

    public abstract DayType dayType();

    public final DayPlan createPlan(LocalDate date, PriceTier priceTier) {
        if (date == null) {
            throw new IllegalArgumentException("date is required");
        }
        if (priceTier == null) {
            throw new IllegalArgumentException("priceTier is required");
        }

        log.info("Creating day plan date={}, dayType={}, priceTier={}", date, dayType(), priceTier);
        DayFulfillment fulfillment = createFulfillment();
        log.info("Day fulfillment rules selected dayType={}, mealRule={}, packagingRule={}, deliveryRule={}",
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

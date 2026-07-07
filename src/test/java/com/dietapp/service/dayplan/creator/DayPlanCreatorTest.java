package com.dietapp.service.dayplan.creator;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.model.DayPlan;
import com.dietapp.model.DayType;
import com.dietapp.model.PriceTier;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DayPlanCreatorTest {

    @Test
    void officeCreatorCreatesOfficeFulfillmentThroughFactoryMethod() {
        DayPlan dayPlan = new OfficeDayPlanCreator()
                .createPlan(LocalDate.of(2026, 7, 6), PriceTier.STANDARD);

        assertThat(dayPlan.dayType()).isEqualTo(DayType.OFFICE);
        assertThat(dayPlan.dailyBudgetInRupees()).isEqualTo(200);
        assertThat(dayPlan.mealRule()).contains("office");
        assertThat(dayPlan.packagingRule()).contains("Leak-proof");
    }

    @Test
    void homeCreatorCreatesHomeFulfillmentThroughFactoryMethod() {
        DayPlan dayPlan = new HomeDayPlanCreator()
                .createPlan(LocalDate.of(2026, 7, 7), PriceTier.PREMIUM);

        assertThat(dayPlan.dayType()).isEqualTo(DayType.HOME);
        assertThat(dayPlan.dailyBudgetInRupees()).isEqualTo(450);
        assertThat(dayPlan.deliveryRule()).contains("home");
    }

    @Test
    void travelCreatorCreatesTravelFulfillmentThroughFactoryMethod() {
        DayPlan dayPlan = new TravelDayPlanCreator()
                .createPlan(LocalDate.of(2026, 7, 8), PriceTier.BUDGET);

        assertThat(dayPlan.dayType()).isEqualTo(DayType.TRAVEL);
        assertThat(dayPlan.dailyBudgetInRupees()).isEqualTo(100);
        assertThat(dayPlan.mealRule()).contains("Portable");
    }
}

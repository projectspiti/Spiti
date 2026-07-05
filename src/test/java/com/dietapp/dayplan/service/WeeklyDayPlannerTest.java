package com.dietapp.dayplan.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.dayplan.creator.HomeDayPlanCreator;
import com.dietapp.dayplan.creator.OfficeDayPlanCreator;
import com.dietapp.dayplan.creator.TravelDayPlanCreator;
import com.dietapp.dayplan.model.DayPlan;
import com.dietapp.dayplan.model.DayType;
import com.dietapp.dayplan.model.PriceTier;
import com.dietapp.dayplan.service.WeeklyDayPlanner.DayPlanInput;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class WeeklyDayPlannerTest {

    private final WeeklyDayPlanner weeklyDayPlanner = new WeeklyDayPlanner(List.of(
            new OfficeDayPlanCreator(),
            new HomeDayPlanCreator(),
            new TravelDayPlanCreator()
    ));

    @Test
    void createsWeeklyPlanUsingTheCreatorForEachDayType() {
        List<DayPlan> dayPlans = weeklyDayPlanner.createWeeklyPlan(List.of(
                new DayPlanInput(LocalDate.of(2026, 7, 6), DayType.OFFICE, PriceTier.STANDARD),
                new DayPlanInput(LocalDate.of(2026, 7, 7), DayType.HOME, PriceTier.PREMIUM),
                new DayPlanInput(LocalDate.of(2026, 7, 8), DayType.TRAVEL, PriceTier.BUDGET)
        ));

        assertThat(dayPlans).extracting(DayPlan::dayType)
                .containsExactly(DayType.OFFICE, DayType.HOME, DayType.TRAVEL);
        assertThat(dayPlans).extracting(DayPlan::dailyBudgetInRupees)
                .containsExactly(200, 450, 100);
    }
}

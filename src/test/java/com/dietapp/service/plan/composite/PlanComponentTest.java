package com.dietapp.service.plan.composite;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanComponentTest {

    @Test
    void calculatesTotalsAtMealDayAndWeekLevels() {
        MealPlanLeaf breakfast = new MealPlanLeaf("Idli bowl", 320, 90);
        MealPlanLeaf lunch = new MealPlanLeaf("Paneer bowl", 560, 275);
        DayPlanComposite monday = new DayPlanComposite("Monday", List.of(breakfast, lunch));

        MealPlanLeaf dinner = new MealPlanLeaf("Dal soup", 280, 120);
        DayPlanComposite tuesday = new DayPlanComposite("Tuesday", List.of(dinner));

        WeekPlanComposite week = new WeekPlanComposite("Week 1", List.of(monday, tuesday));

        assertThat(breakfast.totalCalories()).isEqualTo(320);
        assertThat(monday.totalCalories()).isEqualTo(880);
        assertThat(week.totalCalories()).isEqualTo(1160);
        assertThat(week.totalPriceInRupees()).isEqualTo(485);
    }
}

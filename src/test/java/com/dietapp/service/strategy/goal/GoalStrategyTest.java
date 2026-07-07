package com.dietapp.service.strategy.goal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GoalStrategyTest {

    @Test
    void cutGoalAppliesTwentyPercentDeficit() {
        assertThat(new CutGoalStrategy().applyTo(2500)).isEqualTo(2000);
    }

    @Test
    void bulkGoalAppliesFifteenPercentSurplus() {
        assertThat(new BulkGoalStrategy().applyTo(2500)).isEqualTo(2875);
    }

    @Test
    void leanMuscleGoalAppliesFivePercentSurplus() {
        assertThat(new LeanMuscleGoalStrategy().applyTo(2500)).isEqualTo(2625);
    }

    @Test
    void maintainGoalKeepsCaloriesSame() {
        assertThat(new MaintainGoalStrategy().applyTo(2500)).isEqualTo(2500);
    }
}

package com.dietapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.model.ActivityLevel;
import com.dietapp.model.CalorieTarget;
import com.dietapp.model.Gender;
import com.dietapp.model.GoalType;
import com.dietapp.model.UserProfile;
import com.dietapp.service.strategy.bmr.KatchMcArdleBmrStrategy;
import com.dietapp.service.strategy.bmr.MifflinStJeorBmrStrategy;
import com.dietapp.service.strategy.bmr.PcosAdjustedBmrStrategy;
import com.dietapp.service.strategy.goal.BulkGoalStrategy;
import com.dietapp.service.strategy.goal.CutGoalStrategy;
import com.dietapp.service.strategy.goal.LeanMuscleGoalStrategy;
import com.dietapp.service.strategy.goal.MaintainGoalStrategy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CalorieEngineTest {

    private final MifflinStJeorBmrStrategy mifflin = new MifflinStJeorBmrStrategy();
    private final CalorieEngine calorieEngine = new CalorieEngine(
            new BmrStrategyResolver(List.of(
                    mifflin,
                    new KatchMcArdleBmrStrategy(),
                    new PcosAdjustedBmrStrategy(mifflin)
            )),
            new GoalStrategyResolver(List.of(
                    new CutGoalStrategy(),
                    new BulkGoalStrategy(),
                    new LeanMuscleGoalStrategy(),
                    new MaintainGoalStrategy()
            ))
    );

    @Test
    void calculatesTargetCaloriesUsingSelectedBmrAndGoalStrategies() {
        UserProfile userProfile = new UserProfile(
                28,
                80,
                175,
                Gender.MALE,
                ActivityLevel.MODERATE,
                GoalType.CUT,
                null,
                Set.of()
        );

        CalorieTarget target = calorieEngine.calculateTarget(userProfile);

        assertThat(target.bmr()).isEqualTo(1759);
        assertThat(target.maintenanceCalories()).isEqualTo(2726);
        assertThat(target.targetCalories()).isEqualTo(2181);
        assertThat(target.bmrStrategy()).isEqualTo("MIFFLIN_ST_JEOR");
        assertThat(target.goalStrategy()).isEqualTo("CUT_20_PERCENT_DEFICIT");
    }
}

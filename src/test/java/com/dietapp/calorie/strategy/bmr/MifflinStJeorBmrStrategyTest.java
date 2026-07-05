package com.dietapp.calorie.strategy.bmr;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.calorie.model.ActivityLevel;
import com.dietapp.calorie.model.Gender;
import com.dietapp.calorie.model.GoalType;
import com.dietapp.calorie.model.UserProfile;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MifflinStJeorBmrStrategyTest {

    private final MifflinStJeorBmrStrategy strategy = new MifflinStJeorBmrStrategy();

    @Test
    void calculatesMaleBmr() {
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

        assertThat(strategy.calculateBmr(userProfile)).isEqualTo(1758.75);
    }

    @Test
    void calculatesFemaleBmr() {
        UserProfile userProfile = new UserProfile(
                28,
                80,
                175,
                Gender.FEMALE,
                ActivityLevel.MODERATE,
                GoalType.CUT,
                null,
                Set.of()
        );

        assertThat(strategy.calculateBmr(userProfile)).isEqualTo(1592.75);
    }
}

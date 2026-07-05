package com.dietapp.calorie.strategy.bmr;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.calorie.model.ActivityLevel;
import com.dietapp.calorie.model.Gender;
import com.dietapp.calorie.model.GoalType;
import com.dietapp.calorie.model.UserProfile;
import java.util.Set;
import org.junit.jupiter.api.Test;

class KatchMcArdleBmrStrategyTest {

    private final KatchMcArdleBmrStrategy strategy = new KatchMcArdleBmrStrategy();

    @Test
    void calculatesBmrFromLeanBodyMassWhenBodyFatIsPresent() {
        UserProfile userProfile = new UserProfile(
                28,
                80,
                175,
                Gender.MALE,
                ActivityLevel.MODERATE,
                GoalType.CUT,
                20.0,
                Set.of()
        );

        assertThat(strategy.calculateBmr(userProfile)).isEqualTo(1752.4);
    }
}

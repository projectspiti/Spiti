package com.dietapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dietapp.model.ActivityLevel;
import com.dietapp.model.Gender;
import com.dietapp.model.GoalType;
import com.dietapp.model.MedicalCondition;
import com.dietapp.model.UserProfile;
import com.dietapp.service.strategy.bmr.KatchMcArdleBmrStrategy;
import com.dietapp.service.strategy.bmr.MifflinStJeorBmrStrategy;
import com.dietapp.service.strategy.bmr.PcosAdjustedBmrStrategy;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BmrStrategyResolverTest {

    private final MifflinStJeorBmrStrategy mifflin = new MifflinStJeorBmrStrategy();
    private final BmrStrategyResolver resolver = new BmrStrategyResolver(List.of(
            mifflin,
            new KatchMcArdleBmrStrategy(),
            new PcosAdjustedBmrStrategy(mifflin)
    ));

    @Test
    void selectsPcosStrategyBeforeKatchWhenBothConditionsMatch() {
        UserProfile userProfile = new UserProfile(
                28,
                80,
                175,
                Gender.FEMALE,
                ActivityLevel.MODERATE,
                GoalType.CUT,
                20.0,
                Set.of(MedicalCondition.PCOS)
        );

        assertThat(resolver.resolve(userProfile).name()).isEqualTo("PCOS_ADJUSTED");
    }

    @Test
    void selectsKatchWhenBodyFatIsPresentAndNoHigherPriorityMedicalStrategyMatches() {
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

        assertThat(resolver.resolve(userProfile).name()).isEqualTo("KATCH_MC_ARDLE");
    }
}

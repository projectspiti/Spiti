package com.dietapp.service;

import com.dietapp.model.DayType;
import com.dietapp.model.DietPlanGoal;
import com.dietapp.model.DietPlanTemplateRequest;
import com.dietapp.model.DietPlanTemplateResponse;
import com.dietapp.service.diet.template.BulkDietPlanTemplate;
import com.dietapp.service.diet.template.CutDietPlanTemplate;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DietPlanTemplateServiceTest {

    @Test
    void createsCutPlanUsingTemplateStepOrder() {
        DietPlanTemplateService service = new DietPlanTemplateService(List.of(
                new CutDietPlanTemplate(),
                new BulkDietPlanTemplate()
        ));

        DietPlanTemplateResponse response = service.createPlan(new DietPlanTemplateRequest(
                101L,
                DietPlanGoal.CUT,
                DayType.OFFICE,
                true,
                "12 PM - 8 PM"
        ));

        assertThat(response.calorieTarget()).isEqualTo(1800);
        assertThat(response.meals()).contains("Tofu salad bowl");
        assertThat(response.executedSteps())
                .containsExactly("calcTarget", "pickMeals", "applyDayType", "applyIFWindow");
    }

    @Test
    void createsBulkPlanWithDifferentMealsUsingSameTemplateFlow() {
        DietPlanTemplateService service = new DietPlanTemplateService(List.of(
                new CutDietPlanTemplate(),
                new BulkDietPlanTemplate()
        ));

        DietPlanTemplateResponse response = service.createPlan(new DietPlanTemplateRequest(
                102L,
                DietPlanGoal.BULK,
                DayType.HOME,
                false,
                null
        ));

        assertThat(response.calorieTarget()).isEqualTo(2800);
        assertThat(response.meals()).contains("Paneer rice bowl");
        assertThat(response.fastingRule()).isEqualTo("Intermittent fasting not applied");
    }
}

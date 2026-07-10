package com.dietapp.service.diet.template;

import com.dietapp.model.DietPlanGoal;
import com.dietapp.model.DietPlanTemplateRequest;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CutDietPlanTemplate extends DietPlanTemplate {

    @Override
    public DietPlanGoal goal() {
        return DietPlanGoal.CUT;
    }

    @Override
    protected List<String> pickMeals(DietPlanTemplateRequest request, int calorieTarget, List<String> executedSteps) {
        executedSteps.add("pickMeals");
        return List.of("Oats with whey", "Tofu salad bowl", "Clear soup", "Curd protein snack");
    }
}

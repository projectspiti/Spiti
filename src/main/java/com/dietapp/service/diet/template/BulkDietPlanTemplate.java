package com.dietapp.service.diet.template;

import com.dietapp.model.DietPlanGoal;
import com.dietapp.model.DietPlanTemplateRequest;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BulkDietPlanTemplate extends DietPlanTemplate {

    @Override
    public DietPlanGoal goal() {
        return DietPlanGoal.BULK;
    }

    @Override
    protected List<String> pickMeals(DietPlanTemplateRequest request, int calorieTarget, List<String> executedSteps) {
        executedSteps.add("pickMeals");
        return List.of("Banana peanut smoothie", "Paneer rice bowl", "Millet khichdi", "Greek yogurt with nuts");
    }
}

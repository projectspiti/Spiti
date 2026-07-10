package com.dietapp.service.diet.template;

import com.dietapp.model.DayType;
import com.dietapp.model.DietPlanGoal;
import com.dietapp.model.DietPlanTemplateRequest;
import com.dietapp.model.DietPlanTemplateResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class DietPlanTemplate {

    public final DietPlanTemplateResponse createPlan(DietPlanTemplateRequest request) {
        List<String> executedSteps = new ArrayList<>();
        int calorieTarget = calcTarget(request, executedSteps);
        List<String> meals = pickMeals(request, calorieTarget, executedSteps);
        String dayTypeRule = applyDayType(request.dayType(), executedSteps);
        String fastingRule = applyIFWindow(request, executedSteps);
        return new DietPlanTemplateResponse(
                request.userId(),
                goal(),
                calorieTarget,
                meals,
                dayTypeRule,
                fastingRule,
                executedSteps
        );
    }

    public abstract DietPlanGoal goal();

    protected int calcTarget(DietPlanTemplateRequest request, List<String> executedSteps) {
        executedSteps.add("calcTarget");
        return switch (goal()) {
            case CUT -> 1800;
            case BULK -> 2800;
        };
    }

    protected abstract List<String> pickMeals(
            DietPlanTemplateRequest request,
            int calorieTarget,
            List<String> executedSteps
    );

    protected String applyDayType(DayType dayType, List<String> executedSteps) {
        executedSteps.add("applyDayType");
        return switch (dayType) {
            case OFFICE -> "Office day: leak-proof meals and lunch cutoff delivery";
            case HOME -> "Home day: fresh meals with flexible delivery slot";
            case TRAVEL -> "Travel day: portable meals with compact packaging";
        };
    }

    protected String applyIFWindow(DietPlanTemplateRequest request, List<String> executedSteps) {
        executedSteps.add("applyIFWindow");
        if (!request.intermittentFastingEnabled()) {
            return "Intermittent fasting not applied";
        }
        return "Meals scheduled inside fasting window: " + request.fastingWindow();
    }
}

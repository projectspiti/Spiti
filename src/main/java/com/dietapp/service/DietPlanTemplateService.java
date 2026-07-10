package com.dietapp.service;

import com.dietapp.model.DietPlanGoal;
import com.dietapp.model.DietPlanTemplateRequest;
import com.dietapp.model.DietPlanTemplateResponse;
import com.dietapp.service.diet.template.DietPlanTemplate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DietPlanTemplateService {

    private static final Logger log = LoggerFactory.getLogger(DietPlanTemplateService.class);

    private final Map<DietPlanGoal, DietPlanTemplate> templatesByGoal = new EnumMap<>(DietPlanGoal.class);

    public DietPlanTemplateService(List<DietPlanTemplate> templates) {
        for (DietPlanTemplate template : templates) {
            templatesByGoal.put(template.goal(), template);
        }
        log.info("Loaded {} diet plan templates", templatesByGoal.size());
    }

    public DietPlanTemplateResponse createPlan(DietPlanTemplateRequest request) {
        validate(request);
        DietPlanTemplate template = templatesByGoal.get(request.goal());
        if (template == null) {
            throw new IllegalArgumentException("Unsupported diet plan goal: " + request.goal());
        }
        log.info("Creating diet plan from template userId={}, goal={}, dayType={}",
                request.userId(), request.goal(), request.dayType());
        return template.createPlan(request);
    }

    private void validate(DietPlanTemplateRequest request) {
        if (request.userId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (request.goal() == null) {
            throw new IllegalArgumentException("goal is required");
        }
        if (request.dayType() == null) {
            throw new IllegalArgumentException("dayType is required");
        }
        if (request.intermittentFastingEnabled()
                && (request.fastingWindow() == null || request.fastingWindow().isBlank())) {
            throw new IllegalArgumentException("fastingWindow is required when intermittent fasting is enabled");
        }
    }
}

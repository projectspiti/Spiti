package com.dietapp.model;

import java.util.List;

public record MedicalPlanRouteRequest(
        Long userId,
        List<MedicalCondition> conditions,
        boolean intermittentFastingRequested
) {
}

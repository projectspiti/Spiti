package com.dietapp.model;

import java.util.List;

public record MedicalPlanRouteResponse(
        Long userId,
        List<String> adjustments,
        boolean intermittentFastingAllowed
) {
}

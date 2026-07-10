package com.dietapp.service.medical;

import com.dietapp.model.MedicalCondition;
import java.util.ArrayList;
import java.util.List;

public class MedicalPlanContext {

    private final Long userId;
    private final List<MedicalCondition> conditions;
    private final List<String> adjustments = new ArrayList<>();
    private boolean intermittentFastingAllowed;

    public MedicalPlanContext(Long userId, List<MedicalCondition> conditions, boolean intermittentFastingAllowed) {
        this.userId = userId;
        this.conditions = conditions;
        this.intermittentFastingAllowed = intermittentFastingAllowed;
    }

    public Long userId() {
        return userId;
    }

    public boolean hasCondition(MedicalCondition condition) {
        return conditions.contains(condition);
    }

    public void addAdjustment(String adjustment) {
        adjustments.add(adjustment);
    }

    public List<String> adjustments() {
        return List.copyOf(adjustments);
    }

    public boolean intermittentFastingAllowed() {
        return intermittentFastingAllowed;
    }

    public void disallowIntermittentFasting() {
        intermittentFastingAllowed = false;
    }
}

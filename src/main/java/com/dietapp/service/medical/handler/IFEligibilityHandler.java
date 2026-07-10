package com.dietapp.service.medical.handler;

import com.dietapp.model.MedicalCondition;
import com.dietapp.service.medical.MedicalPlanContext;
import org.springframework.stereotype.Component;

@Component
public class IFEligibilityHandler extends AbstractMedicalPlanHandler {

    @Override
    public int priority() {
        return 40;
    }

    @Override
    protected void adjust(MedicalPlanContext context) {
        if (context.hasCondition(MedicalCondition.DIABETES)) {
            context.disallowIntermittentFasting();
            context.addAdjustment("Intermittent fasting requires doctor review for diabetes");
        }
    }
}

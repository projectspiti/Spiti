package com.dietapp.service.medical.handler;

import com.dietapp.model.MedicalCondition;
import com.dietapp.service.medical.MedicalPlanContext;
import org.springframework.stereotype.Component;

@Component
public class PcosHandler extends AbstractMedicalPlanHandler {

    @Override
    public int priority() {
        return 20;
    }

    @Override
    protected void adjust(MedicalPlanContext context) {
        if (context.hasCondition(MedicalCondition.PCOS)) {
            context.addAdjustment("Prioritize high fiber meals and steady protein distribution");
        }
    }
}

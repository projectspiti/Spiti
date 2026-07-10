package com.dietapp.service.medical.handler;

import com.dietapp.model.MedicalCondition;
import com.dietapp.service.medical.MedicalPlanContext;
import org.springframework.stereotype.Component;

@Component
public class ThyroidHandler extends AbstractMedicalPlanHandler {

    @Override
    public int priority() {
        return 30;
    }

    @Override
    protected void adjust(MedicalPlanContext context) {
        if (context.hasCondition(MedicalCondition.THYROID)) {
            context.addAdjustment("Keep soy-heavy meals away from thyroid medication timing");
        }
    }
}

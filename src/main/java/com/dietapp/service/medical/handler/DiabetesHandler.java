package com.dietapp.service.medical.handler;

import com.dietapp.model.MedicalCondition;
import com.dietapp.service.medical.MedicalPlanContext;
import org.springframework.stereotype.Component;

@Component
public class DiabetesHandler extends AbstractMedicalPlanHandler {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    protected void adjust(MedicalPlanContext context) {
        if (context.hasCondition(MedicalCondition.DIABETES)) {
            context.addAdjustment("Use low glycemic carbs and avoid sugar-heavy snacks");
        }
    }
}

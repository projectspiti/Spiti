package com.dietapp.service;

import com.dietapp.model.MedicalCondition;
import com.dietapp.model.MedicalPlanRouteRequest;
import com.dietapp.model.MedicalPlanRouteResponse;
import com.dietapp.service.medical.handler.DiabetesHandler;
import com.dietapp.service.medical.handler.IFEligibilityHandler;
import com.dietapp.service.medical.handler.PcosHandler;
import com.dietapp.service.medical.handler.ThyroidHandler;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MedicalPlanRouterServiceTest {

    @Test
    void appliesMultipleMedicalHandlersInChain() {
        MedicalPlanRouterService service = new MedicalPlanRouterService(List.of(
                new IFEligibilityHandler(),
                new ThyroidHandler(),
                new PcosHandler(),
                new DiabetesHandler()
        ));

        MedicalPlanRouteResponse response = service.route(new MedicalPlanRouteRequest(
                101L,
                List.of(MedicalCondition.DIABETES, MedicalCondition.PCOS),
                true
        ));

        assertThat(response.adjustments())
                .contains(
                        "Use low glycemic carbs and avoid sugar-heavy snacks",
                        "Prioritize high fiber meals and steady protein distribution",
                        "Intermittent fasting requires doctor review for diabetes"
                );
        assertThat(response.intermittentFastingAllowed()).isFalse();
    }
}

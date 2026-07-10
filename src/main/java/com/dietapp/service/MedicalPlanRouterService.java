package com.dietapp.service;

import com.dietapp.model.MedicalPlanRouteRequest;
import com.dietapp.model.MedicalPlanRouteResponse;
import com.dietapp.service.medical.MedicalPlanContext;
import com.dietapp.service.medical.handler.MedicalPlanHandler;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MedicalPlanRouterService {

    private final MedicalPlanHandler firstHandler;

    public MedicalPlanRouterService(List<MedicalPlanHandler> handlers) {
        if (handlers.isEmpty()) {
            throw new IllegalStateException("At least one medical plan handler is required");
        }
        List<MedicalPlanHandler> orderedHandlers = handlers.stream()
                .sorted(Comparator.comparingInt(MedicalPlanHandler::priority))
                .toList();
        for (int index = 0; index < orderedHandlers.size() - 1; index++) {
            orderedHandlers.get(index).setNext(orderedHandlers.get(index + 1));
        }
        this.firstHandler = orderedHandlers.get(0);
    }

    public MedicalPlanRouteResponse route(MedicalPlanRouteRequest request) {
        validate(request);
        MedicalPlanContext context = new MedicalPlanContext(
                request.userId(),
                request.conditions(),
                request.intermittentFastingRequested()
        );
        firstHandler.handle(context);
        return new MedicalPlanRouteResponse(
                context.userId(),
                context.adjustments(),
                context.intermittentFastingAllowed()
        );
    }

    private void validate(MedicalPlanRouteRequest request) {
        if (request.userId() == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (request.conditions() == null) {
            throw new IllegalArgumentException("conditions are required");
        }
    }
}

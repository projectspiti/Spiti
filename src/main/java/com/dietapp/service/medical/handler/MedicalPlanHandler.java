package com.dietapp.service.medical.handler;

import com.dietapp.service.medical.MedicalPlanContext;

public interface MedicalPlanHandler {

    int priority();

    void setNext(MedicalPlanHandler nextHandler);

    void handle(MedicalPlanContext context);
}

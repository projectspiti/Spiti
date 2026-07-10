package com.dietapp.service.medical.handler;

import com.dietapp.service.medical.MedicalPlanContext;

public abstract class AbstractMedicalPlanHandler implements MedicalPlanHandler {

    private MedicalPlanHandler nextHandler;

    @Override
    public void setNext(MedicalPlanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(MedicalPlanContext context) {
        adjust(context);
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }

    protected abstract void adjust(MedicalPlanContext context);
}

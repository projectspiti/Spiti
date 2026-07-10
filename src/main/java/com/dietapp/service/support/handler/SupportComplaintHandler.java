package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;

public interface SupportComplaintHandler {

    int priority();

    String handlerName();

    void setNext(SupportComplaintHandler nextHandler);

    SupportComplaintResponse handle(SupportComplaintRequest request);
}

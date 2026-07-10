package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import org.springframework.stereotype.Component;

@Component
public class GeneralSupportHandler extends AbstractSupportComplaintHandler {

    @Override
    public int priority() {
        return 100;
    }

    @Override
    public String handlerName() {
        return "GeneralSupportHandler";
    }

    @Override
    protected boolean canHandle(SupportComplaintRequest request) {
        return true;
    }

    @Override
    protected SupportComplaintResponse resolve(SupportComplaintRequest request) {
        return new SupportComplaintResponse(
                request.orderId(),
                request.issueType(),
                handlerName(),
                "General support will review the complaint and route it to the right operations team"
        );
    }
}

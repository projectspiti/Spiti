package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.model.SupportIssueType;
import org.springframework.stereotype.Component;

@Component
public class DeliverySupportHandler extends AbstractSupportComplaintHandler {

    @Override
    public int priority() {
        return 30;
    }

    @Override
    public String handlerName() {
        return "DeliverySupportHandler";
    }

    @Override
    protected boolean canHandle(SupportComplaintRequest request) {
        return request.issueType() == SupportIssueType.DELIVERY_DELAY;
    }

    @Override
    protected SupportComplaintResponse resolve(SupportComplaintRequest request) {
        return new SupportComplaintResponse(
                request.orderId(),
                request.issueType(),
                handlerName(),
                "Delivery support will track the rider and update the customer with a revised ETA"
        );
    }
}

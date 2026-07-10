package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.model.SupportIssueType;
import org.springframework.stereotype.Component;

@Component
public class RefundSupportHandler extends AbstractSupportComplaintHandler {

    @Override
    public int priority() {
        return 40;
    }

    @Override
    public String handlerName() {
        return "RefundSupportHandler";
    }

    @Override
    protected boolean canHandle(SupportComplaintRequest request) {
        return request.issueType() == SupportIssueType.REFUND_REQUEST;
    }

    @Override
    protected SupportComplaintResponse resolve(SupportComplaintRequest request) {
        return new SupportComplaintResponse(
                request.orderId(),
                request.issueType(),
                handlerName(),
                "Refund support will validate eligibility and start the refund workflow"
        );
    }
}

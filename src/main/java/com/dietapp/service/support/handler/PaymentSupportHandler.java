package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.model.SupportIssueType;
import org.springframework.stereotype.Component;

@Component
public class PaymentSupportHandler extends AbstractSupportComplaintHandler {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public String handlerName() {
        return "PaymentSupportHandler";
    }

    @Override
    protected boolean canHandle(SupportComplaintRequest request) {
        return request.issueType() == SupportIssueType.PAYMENT_FAILED;
    }

    @Override
    protected SupportComplaintResponse resolve(SupportComplaintRequest request) {
        return new SupportComplaintResponse(
                request.orderId(),
                request.issueType(),
                handlerName(),
                "Payment support will verify the transaction and retry or mark it for manual review"
        );
    }
}

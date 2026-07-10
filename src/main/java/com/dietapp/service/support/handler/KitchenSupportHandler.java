package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.model.SupportIssueType;
import org.springframework.stereotype.Component;

@Component
public class KitchenSupportHandler extends AbstractSupportComplaintHandler {

    @Override
    public int priority() {
        return 20;
    }

    @Override
    public String handlerName() {
        return "KitchenSupportHandler";
    }

    @Override
    protected boolean canHandle(SupportComplaintRequest request) {
        return request.issueType() == SupportIssueType.FOOD_QUALITY;
    }

    @Override
    protected SupportComplaintResponse resolve(SupportComplaintRequest request) {
        return new SupportComplaintResponse(
                request.orderId(),
                request.issueType(),
                handlerName(),
                "Kitchen support will inspect the meal issue and prepare a replacement or quality report"
        );
    }
}

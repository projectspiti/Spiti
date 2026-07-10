package com.dietapp.service.support.handler;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSupportComplaintHandler implements SupportComplaintHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private SupportComplaintHandler nextHandler;

    @Override
    public void setNext(SupportComplaintHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public SupportComplaintResponse handle(SupportComplaintRequest request) {
        if (canHandle(request)) {
            log.info("Support complaint accepted handler={}, orderId={}, issueType={}",
                    handlerName(), request.orderId(), request.issueType());
            return resolve(request);
        }

        log.info("Support complaint passed to next handler handler={}, orderId={}, issueType={}",
                handlerName(), request.orderId(), request.issueType());
        if (nextHandler == null) {
            throw new IllegalStateException("No support handler found for issueType " + request.issueType());
        }
        return nextHandler.handle(request);
    }

    protected abstract boolean canHandle(SupportComplaintRequest request);

    protected abstract SupportComplaintResponse resolve(SupportComplaintRequest request);
}

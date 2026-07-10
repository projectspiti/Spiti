package com.dietapp.service;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.service.support.handler.SupportComplaintHandler;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SupportComplaintResolverService {

    private static final Logger log = LoggerFactory.getLogger(SupportComplaintResolverService.class);

    private final SupportComplaintHandler firstHandler;

    public SupportComplaintResolverService(List<SupportComplaintHandler> handlers) {
        if (handlers.isEmpty()) {
            throw new IllegalStateException("At least one support complaint handler is required");
        }

        List<SupportComplaintHandler> orderedHandlers = handlers.stream()
                .sorted(Comparator.comparingInt(SupportComplaintHandler::priority))
                .toList();

        for (int index = 0; index < orderedHandlers.size() - 1; index++) {
            orderedHandlers.get(index).setNext(orderedHandlers.get(index + 1));
        }

        this.firstHandler = orderedHandlers.get(0);
        log.info("Loaded {} support complaint handlers firstHandler={}",
                orderedHandlers.size(), firstHandler.handlerName());
    }

    public SupportComplaintResponse resolve(SupportComplaintRequest request) {
        validate(request);
        log.info("Resolving support complaint orderId={}, issueType={}",
                request.orderId(), request.issueType());
        SupportComplaintResponse response = firstHandler.handle(request);
        log.info("Support complaint resolved orderId={}, issueType={}, handledBy={}",
                response.orderId(), response.issueType(), response.handledBy());
        return response;
    }

    private void validate(SupportComplaintRequest request) {
        if (request.orderId() == null || request.orderId().isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
        if (request.issueType() == null) {
            throw new IllegalArgumentException("issueType is required");
        }
        if (request.description() == null || request.description().isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
    }
}

package com.dietapp.model;

public record SupportComplaintResponse(
        String orderId,
        SupportIssueType issueType,
        String handledBy,
        String resolutionMessage
) {
}

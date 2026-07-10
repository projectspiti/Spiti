package com.dietapp.model;

public record SupportComplaintRequest(
        String orderId,
        SupportIssueType issueType,
        String description
) {
}

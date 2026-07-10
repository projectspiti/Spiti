package com.dietapp.service.kitchen;

import org.springframework.stereotype.Component;

@Component
public class KitchenNotificationCoordinatorService {

    public String notifyTeams(String orderId) {
        return "Kitchen and customer notifications queued for order " + orderId;
    }
}

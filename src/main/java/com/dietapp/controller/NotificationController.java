package com.dietapp.controller;

import com.dietapp.model.NotificationRequest;
import com.dietapp.model.NotificationResponse;
import com.dietapp.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public NotificationResponse send(@RequestBody NotificationRequest request) {
        return notificationService.send(request);
    }
}

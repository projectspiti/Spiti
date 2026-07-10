package com.dietapp.controller;

import com.dietapp.model.SupportComplaintRequest;
import com.dietapp.model.SupportComplaintResponse;
import com.dietapp.service.SupportComplaintResolverService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support/complaints")
public class SupportComplaintController {

    private final SupportComplaintResolverService supportComplaintResolverService;

    public SupportComplaintController(SupportComplaintResolverService supportComplaintResolverService) {
        this.supportComplaintResolverService = supportComplaintResolverService;
    }

    @PostMapping("/resolve")
    public SupportComplaintResponse resolve(@RequestBody SupportComplaintRequest request) {
        return supportComplaintResolverService.resolve(request);
    }
}

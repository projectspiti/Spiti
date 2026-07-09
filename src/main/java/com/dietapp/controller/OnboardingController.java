package com.dietapp.controller;

import com.dietapp.model.OnboardingRequest;
import com.dietapp.model.OnboardingResponse;
import com.dietapp.service.OnboardingFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingFacade onboardingFacade;

    public OnboardingController(OnboardingFacade onboardingFacade) {
        this.onboardingFacade = onboardingFacade;
    }

    @PostMapping("/complete")
    public OnboardingResponse completeOnboarding(@RequestBody OnboardingRequest request) {
        return onboardingFacade.completeOnboarding(request);
    }
}

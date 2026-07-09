package com.dietapp.controller;

import com.dietapp.model.WeeklyPlanSummaryRequest;
import com.dietapp.model.WeeklyPlanSummaryResponse;
import com.dietapp.service.WeeklyPlanSummaryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan-summary")
public class WeeklyPlanSummaryController {

    private final WeeklyPlanSummaryService weeklyPlanSummaryService;

    public WeeklyPlanSummaryController(WeeklyPlanSummaryService weeklyPlanSummaryService) {
        this.weeklyPlanSummaryService = weeklyPlanSummaryService;
    }

    @PostMapping("/weekly")
    public WeeklyPlanSummaryResponse summarize(@RequestBody WeeklyPlanSummaryRequest request) {
        return weeklyPlanSummaryService.summarize(request);
    }
}

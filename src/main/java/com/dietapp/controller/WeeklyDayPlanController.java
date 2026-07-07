package com.dietapp.controller;

import com.dietapp.model.DayPlan;
import com.dietapp.service.WeeklyDayPlanner;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/day-plans")
public class WeeklyDayPlanController {

    private final WeeklyDayPlanner weeklyDayPlanner;

    public WeeklyDayPlanController(WeeklyDayPlanner weeklyDayPlanner) {
        this.weeklyDayPlanner = weeklyDayPlanner;
    }

    @PostMapping("/weekly")
    public WeeklyDayPlanResponse createWeeklyPlan(@RequestBody WeeklyDayPlanRequest request) {
        List<DayPlan> dayPlans = weeklyDayPlanner.createWeeklyPlan(request.toInputs());
        return WeeklyDayPlanResponse.from(dayPlans);
    }
}

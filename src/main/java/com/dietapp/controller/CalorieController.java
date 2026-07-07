package com.dietapp.controller;

import com.dietapp.model.CalorieTarget;
import com.dietapp.service.CalorieEngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calories")
public class CalorieController {

    private final CalorieEngine calorieEngine;

    public CalorieController(CalorieEngine calorieEngine) {
        this.calorieEngine = calorieEngine;
    }

    @PostMapping("/target")
    public CalorieTargetResponse calculateTarget(@RequestBody CalorieTargetRequest request) {
        CalorieTarget target = calorieEngine.calculateTarget(request.toUserProfile());
        return CalorieTargetResponse.from(target);
    }
}

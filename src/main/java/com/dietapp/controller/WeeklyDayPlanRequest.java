package com.dietapp.controller;

import com.dietapp.model.DayType;
import com.dietapp.model.PriceTier;
import com.dietapp.service.WeeklyDayPlanner.DayPlanInput;
import java.time.LocalDate;
import java.util.List;

public record WeeklyDayPlanRequest(
        List<DayPlanRequestItem> days
) {

    public List<DayPlanInput> toInputs() {
        if (days == null) {
            return List.of();
        }
        return days.stream()
                .map(day -> new DayPlanInput(day.date(), day.dayType(), day.priceTier()))
                .toList();
    }

    public record DayPlanRequestItem(
            LocalDate date,
            DayType dayType,
            PriceTier priceTier
    ) {
    }
}

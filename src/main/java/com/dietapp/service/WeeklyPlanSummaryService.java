package com.dietapp.service;

import com.dietapp.model.WeeklyPlanSummaryRequest;
import com.dietapp.model.WeeklyPlanSummaryResponse;
import com.dietapp.service.plan.composite.DayPlanComposite;
import com.dietapp.service.plan.composite.MealPlanLeaf;
import com.dietapp.service.plan.composite.PlanComponent;
import com.dietapp.service.plan.composite.WeekPlanComposite;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeeklyPlanSummaryService {

    private static final Logger log = LoggerFactory.getLogger(WeeklyPlanSummaryService.class);

    public WeeklyPlanSummaryResponse summarize(WeeklyPlanSummaryRequest request) {
        validate(request);
        log.info("Creating weekly plan summary weekName={}, days={}", request.weekName(), request.days().size());

        List<DayPlanComposite> dayComponents = request.days().stream()
                .map(this::toDayComponent)
                .toList();

        WeekPlanComposite weekPlan = new WeekPlanComposite(request.weekName(), dayComponents);
        log.info("Weekly plan summary completed weekName={}, totalCalories={}, totalPrice={}",
                weekPlan.name(), weekPlan.totalCalories(), weekPlan.totalPriceInRupees());

        return toResponse(weekPlan);
    }

    private DayPlanComposite toDayComponent(WeeklyPlanSummaryRequest.DaySummaryRequest day) {
        List<PlanComponent> meals = day.meals().stream()
                .map(meal -> new MealPlanLeaf(meal.mealName(), meal.calories(), meal.priceInRupees()))
                .map(PlanComponent.class::cast)
                .toList();
        DayPlanComposite dayPlan = new DayPlanComposite(day.dayName(), meals);
        log.info("Day summary calculated dayName={}, meals={}, totalCalories={}, totalPrice={}",
                dayPlan.name(), dayPlan.meals().size(), dayPlan.totalCalories(), dayPlan.totalPriceInRupees());
        return dayPlan;
    }

    private WeeklyPlanSummaryResponse toResponse(WeekPlanComposite weekPlan) {
        return new WeeklyPlanSummaryResponse(
                weekPlan.name(),
                weekPlan.totalCalories(),
                weekPlan.totalPriceInRupees(),
                weekPlan.days().stream()
                        .map(this::toDayResponse)
                        .toList()
        );
    }

    private WeeklyPlanSummaryResponse.DaySummaryResponse toDayResponse(DayPlanComposite dayPlan) {
        return new WeeklyPlanSummaryResponse.DaySummaryResponse(
                dayPlan.name(),
                dayPlan.totalCalories(),
                dayPlan.totalPriceInRupees(),
                dayPlan.meals().stream()
                        .map(this::toMealResponse)
                        .toList()
        );
    }

    private WeeklyPlanSummaryResponse.MealSummaryResponse toMealResponse(PlanComponent meal) {
        return new WeeklyPlanSummaryResponse.MealSummaryResponse(
                meal.name(),
                meal.totalCalories(),
                meal.totalPriceInRupees()
        );
    }

    private void validate(WeeklyPlanSummaryRequest request) {
        if (request.weekName() == null || request.weekName().isBlank()) {
            throw new IllegalArgumentException("weekName is required");
        }
        if (request.days() == null || request.days().isEmpty()) {
            throw new IllegalArgumentException("days are required");
        }
        for (WeeklyPlanSummaryRequest.DaySummaryRequest day : request.days()) {
            if (day.dayName() == null || day.dayName().isBlank()) {
                throw new IllegalArgumentException("dayName is required");
            }
            if (day.meals() == null || day.meals().isEmpty()) {
                throw new IllegalArgumentException("meals are required");
            }
            for (WeeklyPlanSummaryRequest.MealSummaryRequest meal : day.meals()) {
                if (meal.mealName() == null || meal.mealName().isBlank()) {
                    throw new IllegalArgumentException("mealName is required");
                }
                if (meal.calories() <= 0) {
                    throw new IllegalArgumentException("calories must be greater than zero");
                }
                if (meal.priceInRupees() < 0) {
                    throw new IllegalArgumentException("priceInRupees cannot be negative");
                }
            }
        }
    }
}

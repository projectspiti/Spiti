package com.dietapp.service;

import com.dietapp.service.dayplan.creator.DayPlanCreator;
import com.dietapp.model.DayPlan;
import com.dietapp.model.DayType;
import com.dietapp.model.PriceTier;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WeeklyDayPlanner {

    private static final Logger log = LoggerFactory.getLogger(WeeklyDayPlanner.class);

    private final Map<DayType, DayPlanCreator> creatorsByDayType;

    public WeeklyDayPlanner(List<DayPlanCreator> creators) {
        this.creatorsByDayType = new EnumMap<>(DayType.class);
        log.info("Loaded {} day plan rule providers", creators.size());
        for (DayPlanCreator creator : creators) {
            log.info("Registered day plan rule provider dayType={}", creator.dayType());
            DayPlanCreator existingCreator = creatorsByDayType.put(creator.dayType(), creator);
            if (existingCreator != null) {
                throw new IllegalStateException("Duplicate day plan creator for " + creator.dayType());
            }
        }
    }

    public DayPlan createDayPlan(LocalDate date, DayType dayType, PriceTier priceTier) {
        log.info("Day plan requested date={}, dayType={}, priceTier={}",
                date,
                dayType,
                priceTier);
        DayPlanCreator creator = creatorsByDayType.get(dayType);
        if (creator == null) {
            throw new IllegalArgumentException("Unsupported day type: " + dayType);
        }
        log.info("Day plan rule provider selected dayType={}", dayType);
        return creator.createPlan(date, priceTier);
    }

    public List<DayPlan> createWeeklyPlan(List<DayPlanInput> days) {
        if (days == null || days.isEmpty()) {
            throw new IllegalArgumentException("At least one day is required");
        }

        log.info("Creating weekly plan days={}", days.size());
        return days.stream()
                .map(day -> createDayPlan(day.date(), day.dayType(), day.priceTier()))
                .toList();
    }

    public record DayPlanInput(
            LocalDate date,
            DayType dayType,
            PriceTier priceTier
    ) {
    }
}

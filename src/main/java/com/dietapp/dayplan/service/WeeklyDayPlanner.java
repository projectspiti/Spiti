package com.dietapp.dayplan.service;

import com.dietapp.dayplan.creator.DayPlanCreator;
import com.dietapp.dayplan.model.DayPlan;
import com.dietapp.dayplan.model.DayType;
import com.dietapp.dayplan.model.PriceTier;
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
        log.info("Spring injected {} DayPlanCreator implementations", creators.size());
        for (DayPlanCreator creator : creators) {
            log.info("registering {} for dayType={}",
                    creator.getClass().getSimpleName(),
                    creator.dayType());
            DayPlanCreator existingCreator = creatorsByDayType.put(creator.dayType(), creator);
            if (existingCreator != null) {
                throw new IllegalStateException("Duplicate day plan creator for " + creator.dayType());
            }
        }
    }

    public DayPlan createDayPlan(LocalDate date, DayType dayType, PriceTier priceTier) {
        log.info("request received for date={}, dayType={}, priceTier={}",
                date,
                dayType,
                priceTier);
        DayPlanCreator creator = creatorsByDayType.get(dayType);
        if (creator == null) {
            throw new IllegalArgumentException("Unsupported day type: " + dayType);
        }
        log.info("selected creator class={}", creator.getClass().getSimpleName());
        return creator.createPlan(date, priceTier);
    }

    public List<DayPlan> createWeeklyPlan(List<DayPlanInput> days) {
        if (days == null || days.isEmpty()) {
            throw new IllegalArgumentException("At least one day is required");
        }

        log.info("creating weekly plan for {} day entries", days.size());
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

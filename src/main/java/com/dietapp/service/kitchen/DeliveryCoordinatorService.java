package com.dietapp.service.kitchen;

import com.dietapp.model.DayType;
import org.springframework.stereotype.Component;

@Component
public class DeliveryCoordinatorService {

    public String schedulePickup(DayType dayType) {
        return switch (dayType) {
            case OFFICE -> "Pickup before office lunch cutoff";
            case HOME -> "Pickup in flexible home slot";
            case TRAVEL -> "Pickup before travel departure window";
        };
    }
}

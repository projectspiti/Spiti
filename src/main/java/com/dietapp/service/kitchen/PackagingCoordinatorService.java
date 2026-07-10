package com.dietapp.service.kitchen;

import com.dietapp.model.DayType;
import org.springframework.stereotype.Component;

@Component
public class PackagingCoordinatorService {

    public String selectPackaging(DayType dayType) {
        return switch (dayType) {
            case OFFICE -> "Leak-proof office packaging";
            case HOME -> "Standard hot meal packaging";
            case TRAVEL -> "Compact travel packaging";
        };
    }
}

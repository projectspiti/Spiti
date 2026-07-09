package com.dietapp.service;

import com.dietapp.model.MealAddonType;
import com.dietapp.model.MealCustomizationRequest;
import com.dietapp.model.MealCustomizationResponse;
import com.dietapp.service.meal.AddCurdDecorator;
import com.dietapp.service.meal.BaseMealItem;
import com.dietapp.service.meal.ExtraProteinDecorator;
import com.dietapp.service.meal.MealItem;
import com.dietapp.service.meal.MilletSwapDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealCustomizationService {
    private static final Logger log = LoggerFactory.getLogger(MealCustomizationService.class);

    public MealCustomizationResponse customize(MealCustomizationRequest request) {
        validate(request);

        log.info("Starting meal customization mealName={}, calories={}, protein={}, carbs={}, fat={}, price={}",
                request.mealName(), request.calories(), request.protein(), request.carbs(), request.fat(), request.priceInRupees());

        MealItem mealItem = new BaseMealItem(
                request.mealName(),
                request.calories(),
                request.protein(),
                request.carbs(),
                request.fat(),
                request.priceInRupees()
        );

        for (MealAddonType addon : request.addons()) {
            log.info("Applying meal addon addon={}", addon);
            mealItem = decorate(mealItem, addon);
            log.info("Meal customization updated mealName={}, calories={}, protein={}, carbs={}, fat={}, price={}",
                    mealItem.name(), mealItem.calories(), mealItem.protein(), mealItem.carbs(), mealItem.fat(), mealItem.priceInRupees());
        }

        log.info("Meal customization completed mealName={}, appliedAddons={}", mealItem.name(), mealItem.appliedAddons());
        return new MealCustomizationResponse(
                mealItem.name(),
                mealItem.calories(),
                mealItem.protein(),
                mealItem.carbs(),
                mealItem.fat(),
                mealItem.priceInRupees(),
                mealItem.appliedAddons()
        );
    }

    private MealItem decorate(MealItem mealItem, MealAddonType addon) {
        return switch (addon) {
            case EXTRA_PROTEIN -> new ExtraProteinDecorator(mealItem);
            case ADD_CURD -> new AddCurdDecorator(mealItem);
            case MILLET_SWAP -> new MilletSwapDecorator(mealItem);
        };
    }

    private void validate(MealCustomizationRequest request) {
        if (request.mealName() == null || request.mealName().isBlank()) {
            throw new IllegalArgumentException("mealName is required");
        }
        if (request.calories() <= 0) {
            throw new IllegalArgumentException("calories must be greater than zero");
        }
        if (request.priceInRupees() <= 0) {
            throw new IllegalArgumentException("priceInRupees must be greater than zero");
        }
        if (request.addons() == null) {
            throw new IllegalArgumentException("addons are required; use an empty list when no addons are selected");
        }
    }
}

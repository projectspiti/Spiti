package com.dietapp.service;

import com.dietapp.model.DishTemplate;
import com.dietapp.model.DishTemplateCopy;
import com.dietapp.repository.DishTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KitchenDishPrototypeService {
    private static final Logger log = LoggerFactory.getLogger(KitchenDishPrototypeService.class);

    private final DishTemplateRepository dishTemplateRepository;
    private final PricingConfigService pricingConfigService;

    public KitchenDishPrototypeService(
            DishTemplateRepository dishTemplateRepository,
            PricingConfigService pricingConfigService
    ) {
        this.dishTemplateRepository = dishTemplateRepository;
        this.pricingConfigService = pricingConfigService;
    }

    public DishTemplateCopy duplicateDishTemplate(
            String sourceTemplateCode,
            String newTemplateCode,
            String newDishName,
            int newBasePriceInRupees
    ) {
        log.info("Kitchen admin duplicate dish requested sourceTemplateCode={}, newTemplateCode={}",
                sourceTemplateCode, newTemplateCode);

        DishTemplate prototype = dishTemplateRepository.findPrototype(sourceTemplateCode);
        log.info("Creating dish copy from existing template sourceTemplateCode={}", sourceTemplateCode);

        DishTemplate duplicate = prototype.duplicateForAdmin(
                newTemplateCode,
                newDishName,
                newBasePriceInRupees
        );
        log.info("Dish copy prepared dishName={}, ingredients={}, tags={}",
                duplicate.getDishName(),
                duplicate.getIngredients(), duplicate.getTags());

        int finalPrice = pricingConfigService.finalPrice(
                duplicate.getBasePriceInRupees(),
                duplicate.getPriceTier()
        );
        log.info("Kitchen admin dish copy completed dishName={}, finalPrice={}",
                duplicate.getDishName(), finalPrice);

        return DishTemplateCopy.from(sourceTemplateCode, duplicate, finalPrice);
    }
}

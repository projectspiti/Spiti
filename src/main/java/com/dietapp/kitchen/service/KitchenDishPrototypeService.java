package com.dietapp.kitchen.service;

import com.dietapp.kitchen.model.DishTemplate;
import com.dietapp.kitchen.model.DishTemplateCopy;
import com.dietapp.kitchen.repository.DishTemplateRepository;
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
        log.info("Prototype flow: kitchen admin requested duplicate sourceTemplateCode={}, newTemplateCode={}",
                sourceTemplateCode, newTemplateCode);

        DishTemplate prototype = dishTemplateRepository.findPrototype(sourceTemplateCode);
        log.info("Prototype flow: cloning prototype instead of building dish from scratch");

        DishTemplate duplicate = prototype.duplicateForAdmin(
                newTemplateCode,
                newDishName,
                newBasePriceInRupees
        );
        log.info("Prototype flow: duplicate created with copied ingredients={}, copied tags={}",
                duplicate.getIngredients(), duplicate.getTags());

        int finalPrice = pricingConfigService.finalPrice(
                duplicate.getBasePriceInRupees(),
                duplicate.getPriceTier()
        );
        log.info("Prototype flow: response ready for duplicated dish={}, finalPrice={}",
                duplicate.getDishName(), finalPrice);

        return DishTemplateCopy.from(sourceTemplateCode, duplicate, finalPrice);
    }
}

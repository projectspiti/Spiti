package com.dietapp.service;

import com.dietapp.model.DishPriceTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class PricingConfigService {
    private static final Logger log = LoggerFactory.getLogger(PricingConfigService.class);

    private final Map<DishPriceTier, Integer> tierMarkupInRupees = new EnumMap<>(DishPriceTier.class);

    public PricingConfigService() {
        tierMarkupInRupees.put(DishPriceTier.BUDGET, 0);
        tierMarkupInRupees.put(DishPriceTier.STANDARD, 40);
        tierMarkupInRupees.put(DishPriceTier.PREMIUM, 90);
        log.info("Singleton flow: PricingConfigService created once by Spring with fixed tier markup config");
    }

    public int finalPrice(int basePriceInRupees, DishPriceTier priceTier) {
        log.info("Singleton flow: PricingConfigService.finalPrice() called with basePrice={}, priceTier={}",
                basePriceInRupees, priceTier);
        int markup = tierMarkupInRupees.getOrDefault(priceTier, 0);
        int finalPrice = basePriceInRupees + markup;
        log.info("Singleton flow: final price calculated as basePrice + markup = {} + {} = {}",
                basePriceInRupees, markup, finalPrice);
        return finalPrice;
    }
}

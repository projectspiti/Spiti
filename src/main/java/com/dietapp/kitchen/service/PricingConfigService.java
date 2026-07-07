package com.dietapp.kitchen.service;

import com.dietapp.kitchen.model.PriceTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
public class PricingConfigService {
    private static final Logger log = LoggerFactory.getLogger(PricingConfigService.class);

    private final Map<PriceTier, Integer> tierMarkupInRupees = new EnumMap<>(PriceTier.class);

    public PricingConfigService() {
        tierMarkupInRupees.put(PriceTier.BUDGET, 0);
        tierMarkupInRupees.put(PriceTier.STANDARD, 40);
        tierMarkupInRupees.put(PriceTier.PREMIUM, 90);
        log.info("Singleton flow: PricingConfigService created once by Spring with fixed tier markup config");
    }

    public int finalPrice(int basePriceInRupees, PriceTier priceTier) {
        log.info("Singleton flow: PricingConfigService.finalPrice() called with basePrice={}, priceTier={}",
                basePriceInRupees, priceTier);
        int markup = tierMarkupInRupees.getOrDefault(priceTier, 0);
        int finalPrice = basePriceInRupees + markup;
        log.info("Singleton flow: final price calculated as basePrice + markup = {} + {} = {}",
                basePriceInRupees, markup, finalPrice);
        return finalPrice;
    }
}

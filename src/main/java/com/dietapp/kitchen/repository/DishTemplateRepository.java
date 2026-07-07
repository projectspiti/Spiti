package com.dietapp.kitchen.repository;

import com.dietapp.kitchen.model.DishTemplate;
import com.dietapp.kitchen.model.PriceTier;
import com.dietapp.nutrition.model.NutritionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DishTemplateRepository {
    private static final Logger log = LoggerFactory.getLogger(DishTemplateRepository.class);

    private final Map<String, DishTemplate> templates;

    public DishTemplateRepository() {
        this.templates = Map.of(
                "VEG_PANEER_BOWL",
                new DishTemplate(
                        "VEG_PANEER_BOWL",
                        "Paneer Protein Bowl",
                        NutritionMode.VEG,
                        PriceTier.STANDARD,
                        180,
                        List.of("paneer", "brown rice", "cucumber", "mint chutney"),
                        List.of("high-protein", "veg", "lunch"),
                        "Leak-proof bowl with chutney cup"
                ),
                "NON_VEG_CHICKEN_BOWL",
                new DishTemplate(
                        "NON_VEG_CHICKEN_BOWL",
                        "Chicken Protein Bowl",
                        NutritionMode.NON_VEG,
                        PriceTier.PREMIUM,
                        240,
                        List.of("grilled chicken", "red rice", "sauteed vegetables", "pepper sauce"),
                        List.of("high-protein", "non-veg", "post-workout"),
                        "Compartment box with sauce cup"
                )
        );
        log.info("Prototype flow: DishTemplateRepository initialized with {} prototype templates", templates.size());
    }

    public DishTemplate findPrototype(String templateCode) {
        log.info("Prototype flow: looking up prototype templateCode={}", templateCode);
        DishTemplate template = templates.get(templateCode);
        if (template == null) {
            throw new IllegalArgumentException("Unknown dish template: " + templateCode);
        }
        log.info("Prototype flow: found prototype dishName={}, priceTier={}",
                template.getDishName(), template.getPriceTier());
        return template;
    }
}

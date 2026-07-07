package com.dietapp.service;

import com.dietapp.model.DishTemplateCopy;
import com.dietapp.repository.DishTemplateRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KitchenDishPrototypeServiceTest {

    @Test
    void duplicatesTemplateAndUsesPricingSingletonService() {
        KitchenDishPrototypeService service = new KitchenDishPrototypeService(
                new DishTemplateRepository(),
                new PricingConfigService()
        );

        DishTemplateCopy copy = service.duplicateDishTemplate(
                "VEG_PANEER_BOWL",
                "VEG_SPICY_PANEER_BOWL",
                "Spicy Paneer Protein Bowl",
                190
        );

        assertThat(copy.sourceTemplateCode()).isEqualTo("VEG_PANEER_BOWL");
        assertThat(copy.newTemplateCode()).isEqualTo("VEG_SPICY_PANEER_BOWL");
        assertThat(copy.dishName()).isEqualTo("Spicy Paneer Protein Bowl");
        assertThat(copy.ingredients()).contains("paneer", "brown rice");
        assertThat(copy.tags()).contains("high-protein", "veg");
        assertThat(copy.finalPriceInRupees()).isEqualTo(230);
    }
}

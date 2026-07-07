package com.dietapp.kitchen.model;

import com.dietapp.nutrition.model.NutritionMode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DishTemplateTest {

    @Test
    void duplicateForAdminCopiesPrototypeFieldsButChangesRequestedFields() {
        DishTemplate prototype = new DishTemplate(
                "VEG_PANEER_BOWL",
                "Paneer Protein Bowl",
                NutritionMode.VEG,
                PriceTier.STANDARD,
                180,
                List.of("paneer", "brown rice"),
                List.of("high-protein", "veg"),
                "Leak-proof bowl"
        );

        DishTemplate duplicate = prototype.duplicateForAdmin(
                "VEG_TOFU_BOWL",
                "Tofu Protein Bowl",
                170
        );

        assertThat(duplicate.getTemplateCode()).isEqualTo("VEG_TOFU_BOWL");
        assertThat(duplicate.getDishName()).isEqualTo("Tofu Protein Bowl");
        assertThat(duplicate.getBasePriceInRupees()).isEqualTo(170);
        assertThat(duplicate.getNutritionMode()).isEqualTo(NutritionMode.VEG);
        assertThat(duplicate.getPriceTier()).isEqualTo(PriceTier.STANDARD);
        assertThat(duplicate.getIngredients()).containsExactly("paneer", "brown rice");
        assertThat(duplicate.getTags()).containsExactly("high-protein", "veg");
        assertThat(duplicate.getPackagingRule()).isEqualTo("Leak-proof bowl");
    }

    @Test
    void duplicateHasImmutableCopiedLists() {
        DishTemplate prototype = new DishTemplate(
                "VEG_PANEER_BOWL",
                "Paneer Protein Bowl",
                NutritionMode.VEG,
                PriceTier.STANDARD,
                180,
                List.of("paneer", "brown rice"),
                List.of("high-protein", "veg"),
                "Leak-proof bowl"
        );

        DishTemplate duplicate = prototype.duplicateForAdmin("VEG_TOFU_BOWL", "Tofu Protein Bowl", 170);

        assertThatThrownBy(() -> duplicate.getIngredients().add("changed later"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}

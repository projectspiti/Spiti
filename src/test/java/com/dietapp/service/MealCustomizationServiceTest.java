package com.dietapp.service;

import com.dietapp.model.MealAddonType;
import com.dietapp.model.MealCustomizationRequest;
import com.dietapp.model.MealCustomizationResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MealCustomizationServiceTest {

    @Test
    void customizesMealByApplyingDecoratorsInRequestOrder() {
        MealCustomizationService service = new MealCustomizationService();

        MealCustomizationResponse response = service.customize(new MealCustomizationRequest(
                "Paneer Bowl",
                420,
                32,
                45,
                14,
                165,
                List.of(MealAddonType.EXTRA_PROTEIN, MealAddonType.ADD_CURD, MealAddonType.MILLET_SWAP)
        ));

        assertThat(response.name()).isEqualTo("Paneer Bowl + Extra Protein + Curd + Millet Swap");
        assertThat(response.calories()).isEqualTo(560);
        assertThat(response.protein()).isEqualTo(62);
        assertThat(response.carbs()).isEqualTo(41);
        assertThat(response.fat()).isEqualTo(19);
        assertThat(response.priceInRupees()).isEqualTo(275);
        assertThat(response.appliedAddons()).containsExactly("Extra Protein", "Curd", "Millet Swap");
    }

    @Test
    void rejectsInvalidCaloriesBeforeDecorating() {
        MealCustomizationService service = new MealCustomizationService();

        assertThatThrownBy(() -> service.customize(new MealCustomizationRequest(
                "Paneer Bowl",
                0,
                32,
                45,
                14,
                165,
                List.of()
        )))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("calories must be greater than zero");
    }
}

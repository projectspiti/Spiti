package com.dietapp.service.meal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MealItemDecoratorTest {

    @Test
    void decoratorsAddBehaviorWithoutChangingBaseMealClass() {
        MealItem mealItem = new BaseMealItem("Paneer Bowl", 420, 32, 45, 14, 165);

        mealItem = new ExtraProteinDecorator(mealItem);
        mealItem = new AddCurdDecorator(mealItem);
        mealItem = new MilletSwapDecorator(mealItem);

        assertThat(mealItem.name()).isEqualTo("Paneer Bowl + Extra Protein + Curd + Millet Swap");
        assertThat(mealItem.calories()).isEqualTo(560);
        assertThat(mealItem.protein()).isEqualTo(62);
        assertThat(mealItem.carbs()).isEqualTo(41);
        assertThat(mealItem.fat()).isEqualTo(19);
        assertThat(mealItem.priceInRupees()).isEqualTo(275);
        assertThat(mealItem.appliedAddons()).containsExactly("Extra Protein", "Curd", "Millet Swap");
    }
}

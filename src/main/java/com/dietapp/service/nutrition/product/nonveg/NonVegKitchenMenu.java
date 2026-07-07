package com.dietapp.service.nutrition.product.nonveg;

import com.dietapp.service.nutrition.product.KitchenMenu;
import java.util.List;

public class NonVegKitchenMenu implements KitchenMenu {

    @Override
    public String name() {
        return "NonVegKitchenMenu";
    }

    @Override
    public List<String> availableItems() {
        return List.of("Chicken protein bowl", "Egg bhurji wrap", "Fish curry meal box");
    }
}

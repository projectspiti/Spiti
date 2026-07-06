package com.dietapp.nutrition.product.veg;

import com.dietapp.nutrition.product.KitchenMenu;
import java.util.List;

public class VegKitchenMenu implements KitchenMenu {

    @Override
    public String name() {
        return "VegKitchenMenu";
    }

    @Override
    public List<String> availableItems() {
        return List.of("Paneer protein bowl", "Soya keema wrap", "Dal and millet meal box");
    }
}

package com.dietapp.nutrition.product.veg;

import com.dietapp.nutrition.product.MacroBlockSet;
import java.util.List;

public class VegMacroBlockSet implements MacroBlockSet {

    @Override
    public String name() {
        return "VegMacroBlockSet";
    }

    @Override
    public List<String> proteinSources() {
        return List.of("paneer", "tofu", "dal", "greek yogurt");
    }

    @Override
    public List<String> carbSources() {
        return List.of("brown rice", "millet", "roti", "sweet potato");
    }
}

package com.dietapp.nutrition.product.nonveg;

import com.dietapp.nutrition.product.MacroBlockSet;
import java.util.List;

public class NonVegMacroBlockSet implements MacroBlockSet {

    @Override
    public String name() {
        return "NonVegMacroBlockSet";
    }

    @Override
    public List<String> proteinSources() {
        return List.of("chicken breast", "eggs", "fish", "lean mutton");
    }

    @Override
    public List<String> carbSources() {
        return List.of("red rice", "brown rice", "roti", "potato mash");
    }
}

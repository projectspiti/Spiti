package com.dietapp.nutrition.product;

import java.util.List;

public interface MacroBlockSet {
    String name();

    List<String> proteinSources();

    List<String> carbSources();
}

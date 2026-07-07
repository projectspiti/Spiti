package com.dietapp.kitchen.controller;

import com.dietapp.kitchen.model.DishTemplateCopy;
import com.dietapp.kitchen.service.KitchenDishPrototypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kitchen-admin/dish-templates")
public class KitchenAdminDishController {
    private final KitchenDishPrototypeService kitchenDishPrototypeService;

    public KitchenAdminDishController(KitchenDishPrototypeService kitchenDishPrototypeService) {
        this.kitchenDishPrototypeService = kitchenDishPrototypeService;
    }

    @PostMapping("/duplicate")
    public DishTemplateCopy duplicateTemplate(@RequestBody DishTemplateDuplicateRequest request) {
        return kitchenDishPrototypeService.duplicateDishTemplate(
                request.sourceTemplateCode(),
                request.newTemplateCode(),
                request.newDishName(),
                request.newBasePriceInRupees()
        );
    }
}

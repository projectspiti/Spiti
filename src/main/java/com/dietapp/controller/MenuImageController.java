package com.dietapp.controller;

import com.dietapp.model.MenuImageResponse;
import com.dietapp.service.menu.MenuImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu/images")
public class MenuImageController {

    private final MenuImageService menuImageService;

    public MenuImageController(MenuImageService menuImageService) {
        this.menuImageService = menuImageService;
    }

    @GetMapping("/{dishCode}")
    public MenuImageResponse getImage(@PathVariable String dishCode) {
        return menuImageService.getImage(dishCode);
    }
}

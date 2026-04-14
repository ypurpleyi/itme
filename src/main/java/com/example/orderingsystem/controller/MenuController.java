package com.example.orderingsystem.controller;

import com.example.orderingsystem.dto.UpdateImageRequest;
import com.example.orderingsystem.dto.UpdatePriceRequest;
import com.example.orderingsystem.entity.MenuItem;
import com.example.orderingsystem.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuItem> listMenuItems() {
        return menuService.listMenuItems();
    }

    @PatchMapping("/{menuItemId}/price")
    public MenuItem updatePrice(@PathVariable Long menuItemId,
                                @Valid @RequestBody UpdatePriceRequest request) {
        return menuService.updatePrice(menuItemId, request);
    }

    @PatchMapping("/{menuItemId}/image")
    public MenuItem updateImage(@PathVariable Long menuItemId,
                                @Valid @RequestBody UpdateImageRequest request) {
        return menuService.updateImage(menuItemId, request);
    }
}

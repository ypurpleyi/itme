package com.example.orderingsystem.service;

import com.example.orderingsystem.dto.UpdateImageRequest;
import com.example.orderingsystem.dto.UpdatePriceRequest;
import com.example.orderingsystem.entity.MenuItem;
import com.example.orderingsystem.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    public MenuService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> listMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem updatePrice(Long menuItemId, UpdatePriceRequest request) {
        MenuItem item = getMenuItem(menuItemId);
        item.setPrice(request.price());
        item.setPriceEditHint("提示：修改价格后请通知前台和线上渠道，避免前后端价格不一致。");
        return menuItemRepository.save(item);
    }

    public MenuItem updateImage(Long menuItemId, UpdateImageRequest request) {
        MenuItem item = getMenuItem(menuItemId);
        item.setImageUrl(request.imageUrl());
        item.setImageUploadHint("提示：请上传清晰菜品图（建议1:1比例，大小不超过2MB）。");
        return menuItemRepository.save(item);
    }

    public MenuItem getMenuItem(Long menuItemId) {
        return menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new IllegalArgumentException("未找到菜品，ID=" + menuItemId));
    }
}

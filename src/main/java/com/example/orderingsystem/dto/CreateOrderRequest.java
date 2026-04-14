package com.example.orderingsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotNull(message = "菜品ID不能为空")
        Long menuItemId,

        @Min(value = 1, message = "下单数量至少为1")
        Integer quantity,

        @NotBlank(message = "客户名称不能为空")
        String customerName
) {
}

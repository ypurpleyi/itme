package com.example.orderingsystem.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdatePriceRequest(
        @NotNull(message = "新价格不能为空")
        @DecimalMin(value = "0.01", message = "价格必须大于0")
        BigDecimal price
) {
}

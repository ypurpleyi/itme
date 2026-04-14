package com.example.orderingsystem.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateImageRequest(
        @NotBlank(message = "图片地址不能为空")
        String imageUrl
) {
}

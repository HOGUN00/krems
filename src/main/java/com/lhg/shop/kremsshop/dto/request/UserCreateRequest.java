package com.lhg.shop.kremsshop.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Nickname is required")
        String nickname
) {
}

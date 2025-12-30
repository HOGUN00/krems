package com.lhg.shop.kremsshop.dto.request;

public record UserUpdateRequest(
        String nickname,
        String email,
        String password
) {
}

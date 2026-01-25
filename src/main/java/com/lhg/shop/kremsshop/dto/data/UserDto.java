package com.lhg.shop.kremsshop.dto.data;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(
        Long id,
        String nickname,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

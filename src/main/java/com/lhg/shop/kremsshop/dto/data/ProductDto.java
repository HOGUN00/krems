package com.lhg.shop.kremsshop.dto.data;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductDto (
    Long id,
    String name,
    Double price,
    Integer stockQuantity,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}

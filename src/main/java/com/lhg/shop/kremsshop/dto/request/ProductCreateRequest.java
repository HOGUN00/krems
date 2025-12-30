package com.lhg.shop.kremsshop.dto.request;


import jakarta.validation.constraints.NotBlank;

public record ProductCreateRequest(
        @NotBlank(message = "Name must not be blank")
        String name,
        String description,
        @NotBlank(message = "Price must not be blank")
        Double price,
        @NotBlank(message = "Stock must not be blank")
        Integer stockQuantity
) {
}

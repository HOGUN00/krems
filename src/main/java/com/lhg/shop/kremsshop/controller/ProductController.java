package com.lhg.shop.kremsshop.controller;

import com.lhg.shop.kremsshop.dto.data.ProductDto;
import com.lhg.shop.kremsshop.dto.request.ProductCreateRequest;
import com.lhg.shop.kremsshop.entity.Product;
import com.lhg.shop.kremsshop.mapper.ProductMapper;
import com.lhg.shop.kremsshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductCreateRequest request) {
        Product product = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDto(product));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(
            @PathVariable Long productId) {
        Product product = productService.findById(productId);
        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PutMapping("/{productId}/stock")
    public ResponseEntity<ProductDto> updateProductStock(
            @PathVariable Long productId,
            @RequestParam Integer stockQuantity) {
        Product updatedProduct = productService.updateStockQuantity(productId, stockQuantity);
        return ResponseEntity.ok(productMapper.toDto(updatedProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }
}

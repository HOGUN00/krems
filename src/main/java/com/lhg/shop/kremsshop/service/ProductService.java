package com.lhg.shop.kremsshop.service;

import com.lhg.shop.kremsshop.dto.request.ProductCreateRequest;
import com.lhg.shop.kremsshop.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductCreateRequest product);
    Product findById(Long id);
    List<Product> findAll();
    Product updateStockQuantity(Long id, Integer stockQuantity);
    void deleteById(Long id);
}

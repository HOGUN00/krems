package com.lhg.shop.kremsshop.service.impl;

import com.lhg.shop.kremsshop.dto.request.ProductCreateRequest;
import com.lhg.shop.kremsshop.entity.Product;
import com.lhg.shop.kremsshop.repository.ProductRepository;
import com.lhg.shop.kremsshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(ProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .build();

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product updateStockQuantity(Long id, Integer newStockQuantity) {
        Product product = productRepository.findById(id)
                        .orElseThrow();

        product.update(newStockQuantity);

        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

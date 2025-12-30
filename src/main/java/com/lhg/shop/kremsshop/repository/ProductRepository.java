package com.lhg.shop.kremsshop.repository;

import com.lhg.shop.kremsshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

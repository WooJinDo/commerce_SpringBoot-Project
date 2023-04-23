package com.zerobase.minicommerce.product.repository;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Category findByCategoryName(String categoryName);
}

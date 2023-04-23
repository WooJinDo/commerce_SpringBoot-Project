package com.zerobase.minicommerce.product.repository;

import com.zerobase.minicommerce.product.domain.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
}

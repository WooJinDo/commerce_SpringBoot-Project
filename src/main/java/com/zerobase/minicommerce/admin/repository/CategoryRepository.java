package com.zerobase.minicommerce.admin.repository;

import com.zerobase.minicommerce.admin.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Category findByCategoryName(String categoryName);
}

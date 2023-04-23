package com.zerobase.minicommerce.product.service;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.product.domain.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImgService {
    /* 상품 이미지 저장 */
    void saveProductImg(Product product, MultipartFile fileInput) throws IOException;
    /* 상품 이미지 수정 */
    void updateProductImg(Long product, MultipartFile fileInput) throws IOException;

}

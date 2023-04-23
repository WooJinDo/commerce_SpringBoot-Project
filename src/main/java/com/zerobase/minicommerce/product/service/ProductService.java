package com.zerobase.minicommerce.product.service;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.product.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    /* 관리자 - 상품 등록 */
    ProductDto.Response saveProduct(ProductDto.Request request, MultipartFile fileInput) throws IOException;

    /* 관리자 - 상품 목록 조회 */
    List<ProductDto.AdminListResponse> adminProductSelectList();

    /* 관리자 - 상품 상세 조회 */
    ProductDto.AdminDetailResponse adminProductSelectDetail(Long productId);

    /* 관리자 - 상품 수정 */
    ProductDto.UpdateResponse updateProduct(ProductDto.UpdateRequest request, MultipartFile fileInput) throws IOException;

    /* 사용자 - 상품 목록 조회 */
    List<ProductDto.FrontProductList> selectFrontProductList(Long categoryId);

    /* 사용자 - 상품 상세 조회 */
    ProductDto.FrontProductDetail selectFrontProductDetail(Long productId);
}

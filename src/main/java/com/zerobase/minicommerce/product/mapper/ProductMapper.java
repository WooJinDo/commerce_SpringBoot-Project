package com.zerobase.minicommerce.product.mapper;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.product.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    /* 관리자 - 상품 목록 조회 */
    List<ProductDto.AdminListResponse> adminProductSelectList();

    /* 관리자 - 상품 상세 조회 */
    ProductDto.AdminDetailResponse adminProductSelectDetail(Long productId);

    /* 사용자 - 상품 목록 조회 */
    List<ProductDto.FrontProductList> selectFrontProductList(Long categoryId);

    /* 사용자 - 상품 상세 조회 */
    ProductDto.FrontProductDetail selectFrontProductDetail(Long productId);
}

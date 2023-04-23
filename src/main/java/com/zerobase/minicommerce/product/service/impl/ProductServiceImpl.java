package com.zerobase.minicommerce.product.service.impl;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.admin.mapper.CategoryMapper;
import com.zerobase.minicommerce.admin.repository.CategoryRepository;
import com.zerobase.minicommerce.admin.service.CategoryImgService;
import com.zerobase.minicommerce.admin.service.CategoryService;
import com.zerobase.minicommerce.product.domain.Product;
import com.zerobase.minicommerce.product.dto.ProductDto;
import com.zerobase.minicommerce.product.mapper.ProductMapper;
import com.zerobase.minicommerce.product.repository.ProductRepository;
import com.zerobase.minicommerce.product.service.ProductImgService;
import com.zerobase.minicommerce.product.service.ProductService;
import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductImgService productImgService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /* 관리자 - 상품 등록 */
    @Transactional
    @Override
    public ProductDto.Response saveProduct(ProductDto.Request request, MultipartFile fileInput) throws IOException {
        // 상품 등록
        Product product = productRepository.save(
                Product.builder()
                        .categoryId(request.getCategoryId())
                        .productName(request.getProductName())
                        .productPrice(request.getProductPrice())
                        .productDescription(request.getProductDescription())
                        .productStock(request.getProductStock())
                        .productStatus(ProductStatus.SELL)
                        .publishStatus(request.isPublishStatus())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        // 상품 이미지 등록
        productImgService.saveProductImg(product, fileInput);

        return ProductDto.Response.fromEntity(true);
    }

    /* 관리자 - 상품 목록 조회 */
    @Transactional(readOnly = true)
    @Override
    public List<ProductDto.AdminListResponse> adminProductSelectList() {
        return productMapper.adminProductSelectList();
    }

    /* 관리자 - 상품 상세 조회 */
    @Transactional(readOnly = true)
    @Override
    public ProductDto.AdminDetailResponse adminProductSelectDetail(Long productId) {
        return productMapper.adminProductSelectDetail(productId);
    }

    /* 관리자 - 상품 수정 */
    @Transactional
    @Override
    public ProductDto.UpdateResponse updateProduct(ProductDto.UpdateRequest request, MultipartFile fileInput) throws IOException {
        // 상품 수정
        Product product = productRepository.findById(request.getProductId()).orElseThrow(EntityNotFoundException::new);
        product.updateProduct(request);

        if (fileInput != null) {
            // 상품 이미지 수정
            productImgService.updateProductImg(request.getImgId(), fileInput);
        }

        return ProductDto.UpdateResponse.fromEntity(true);
    }

    /* 사용자 - 상품 목록 조회 */
    @Transactional(readOnly = true)
    @Override
    public List<ProductDto.FrontProductList> selectFrontProductList(Long categoryId) {
        return productMapper.selectFrontProductList(categoryId);
    }

    /* 사용자 - 상품 상세 조회 */
    @Transactional(readOnly = true)
    @Override
    public ProductDto.FrontProductDetail selectFrontProductDetail(Long productId) {
        return productMapper.selectFrontProductDetail(productId);
    }
}

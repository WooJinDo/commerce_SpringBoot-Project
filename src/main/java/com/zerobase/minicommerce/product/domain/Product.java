package com.zerobase.minicommerce.product.domain;

import com.zerobase.minicommerce.product.dto.ProductDto;
import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* 상품 도메인
* */
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /* 상품 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    /* 카테고리 아이디 */
    private Long categoryId;

    /* 상품명 */
    private String productName;

    /* 상품가격 */
    private int productPrice;

    /* 상품설명 */
    private String productDescription;

    /* 재고수량 */
    private int productStock;

    /* 판매상태 - 판매중, 품절 */
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    /* 게시여부 */
    private boolean publishStatus;

    /* 등록일 */
    private LocalDateTime createdAt;

    /* 수정일 */
    private LocalDateTime updatedAt;

//    public static Product updateProduct(ProductDto.UpdateRequest request) {
//        return Product.builder()
//                .categoryId(request.getCategoryId())
//                .productName(request.getProductName())
//                .productPrice(request.getProductPrice())
//                .productStock(request.getProductStock())
//                .productDescription(request.getProductDescription())
//                .productStatus(ProductStatus.SELL)
//                .publishStatus(request.isPublishStatus())
//                .updatedAt(LocalDateTime.now())
//                .build();
//    }

    // 관리자 - 상품 수정 DB update
    public void updateProduct(ProductDto.UpdateRequest request) {
        this.categoryId = request.getCategoryId();
        this.productName = request.getProductName();
        this.productPrice = request.getProductPrice();
        this.productStock = request.getProductStock();
        this.productDescription = request.getProductDescription();
        this.productStatus = ProductStatus.SELL;
        this.publishStatus = request.isPublishStatus();
        this.updatedAt = LocalDateTime.now();
    }
}


package com.zerobase.minicommerce.product.dto;

import com.zerobase.minicommerce.product.domain.Product;
import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* 상품 DTO
* */
public class ProductDto {
    /* 관리자 - 상품 등록 요청 */
    @Getter
    @Setter
    @ToString
    public static class Request{
        /* 카테고리 아이디 */
        @NotNull(message = "카테고리를 선택해주세요.")
        private Long categoryId;

        /* 상품명 */
        @NotBlank(message = "상품이름은 상품 등록 시 필수입니다.")
        private String productName;

        /* 상품가격 */
        @Positive(message = "상품은 0원 이상일 때 등록 가능합니다.")
        private int productPrice;

        /* 상품설명 */
        @NotBlank(message = "상품 내용을 입력하세요.")
        private String productDescription;

        /* 재고수량 */
        @Min(value = 10, message = "상품의 재고는 10개 이상이어야 합니다.")
        private int productStock;

        /* 판매상태 - 판매중, 품절 */
//        private ProductStatus productStatus;

        /* 게시여부 */
        private boolean publishStatus;
    }

    /* 관리자 - 상품 등록 응답 */
    @Getter @Setter
    @Builder
    public static class Response{
        private boolean success;

        public static Response fromEntity(boolean success) {
            return Response.builder()
                    .success(success)
                    .build();
        }

    }

    /* 관리자 - 상품 목록 */
    @Getter @Setter
    public static class AdminListResponse{
        /* 상품 아이디 */
        private Long productId;

        /* 상품명 */
        private String productName;

        /* 상품가격 */
        private int productPrice;

        /* 판매상태 - 판매중, 품절 */
        private ProductStatus productStatus;

        /* 게시여부 */
        private boolean publishStatus;

        /* 카테고리 아이디 */
        private Long categoryId;

        /* 카테고리명 */
        private String categoryName;

        /* 등록일 */
        private LocalDateTime createdAt;

        /* 시간 형식 변경  */
        public String getCreatedAtText() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            return createdAt != null ? createdAt.format(formatter) : "";
        }

    }

    /* 관리자 - 상품 상세 */
    @Getter @Setter
    @ToString
    public static class AdminDetailResponse{
        /* 상품 아이디 */
        private Long productId;

        /* 상품명 */
        private String productName;

        /* 상품가격 */
        private int productPrice;

        /* 재고수량 */
        private int productStock;

        /* 상품설명 */
        private String productDescription;

        /* 판매상태 - 판매중, 품절 */
        private ProductStatus productStatus;

        /* 게시여부 */
        private boolean publishStatus;

        /* 카테고리 아이디 */
        private Long categoryId;

        /* 카테고리명 */
        private String categoryName;

        /* 이미지 아이디 */
        private Long imgId;

        /* 출력 이미지 url 경로 */
//        private String imgUrlPath;

        /* 원본 파일명 */
        private String imgOriginName;
    }

    /* 관리자 - 상품 수정 요청 */
    @Getter
    @Setter
    @ToString
    public static class UpdateRequest{
        /* 상품 아이디 */
        private Long productId;

        /* 카테고리 아이디 */
        @NotNull(message = "카테고리를 선택해주세요.")
        private Long categoryId;

        /* 상품명 */
        @NotBlank(message = "상품이름은 상품 등록 시 필수입니다.")
        private String productName;

        /* 상품가격 */
        @Positive(message = "상품은 0원 이상일 때 등록 가능합니다.")
        private int productPrice;

        /* 상품설명 */
        @NotBlank(message = "상품 내용을 입력하세요.")
        private String productDescription;

        /* 재고수량 */
        @Min(value = 10, message = "상품의 재고는 10개 이상이어야 합니다.")
        private int productStock;

        /* 판매상태 - 판매중, 품절 */
//        private ProductStatus productStatus;

        /* 게시여부 */
        private boolean publishStatus;

        /* 이미지 아이디 */
        private Long imgId;
    }

    /* 관리자 - 상품 수정 응답 */
    @Getter @Setter
    @Builder
    public static class UpdateResponse{
        private boolean success;

        public static UpdateResponse fromEntity(boolean success) {
            return UpdateResponse.builder()
                    .success(success)
                    .build();
        }

    }

    /* 사용자 - 상품 목록 조회 */
    @Getter @Setter
    public static class FrontProductList{
        /* 상품 아이디 */
        private Long productId;

        /* 상품명 */
        private String productName;

        /* 상품가격 */
        private int productPrice;

        /* 판매상태 - 판매중, 품절 */
        private ProductStatus productStatus;

        /* 게시여부 */
        private boolean publishStatus;

        /* 출력 이미지 url 경로 */
        private String imgUrlPath;

        /* 원본 파일명 */
        private String imgOriginName;

    }

    /* 사용자 - 상품 상세 조회 */
    @Getter @Setter
    @ToString
    public static class FrontProductDetail{
        /* 상품 아이디 */
        private Long productId;

        /* 상품명 */
        private String productName;

        /* 상품가격 */
        private int productPrice;

        /* 재고수량 */
        private int productStock;

        /* 판매상태 - 판매중, 품절 */
        private ProductStatus productStatus;

        /* 상품설명 */
        private String productDescription;

        /* 출력 이미지 url 경로 */
        private String imgUrlPath;

        /* 원본 파일명 */
        private String imgOriginName;

        /* 카테고리 아이디 */
        private Long categoryId;

        /* 카테고리명 */
        private String categoryName;

    }
}

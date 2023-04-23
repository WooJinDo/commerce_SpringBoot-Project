package com.zerobase.minicommerce.product.domain;

import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* 상품 이미지 도메인
* */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImg {

    /* 이미지 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    /* 상품 아이디 */
    private Long productId;

    /* 원본 파일명 */
    private String imgOriginName;

    /* 저장 파일명 */
    private String imgSaveName;

    /* 이미지 경로 */
    private String imgPath;

    /* 출력 이미지 url 경로 */
    private String imgUrlPath;

    /* 등록일 */
    private LocalDateTime createdAt;

    /* 수정일 */
    private LocalDateTime updatedAt;

    // 관리자 상품 이미지 DB update
    public void updateProductImg(String imgOriginName, String imgSaveName, String imgPath, String imgUrlPath) {
        this.imgOriginName = imgOriginName;
        this.imgSaveName = imgSaveName;
        this.imgPath = imgPath;
        this.imgUrlPath = imgUrlPath;
        this.updatedAt = LocalDateTime.now();
    }
}

package com.zerobase.minicommerce.admin.domain;

import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* 카테고리 도메인
* */
@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    /* 카테고리 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    /* 카테고리명 */
    private String categoryName;

    /* 등록일 */
    private LocalDateTime createdAt;
}


package com.zerobase.minicommerce.admin.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
* 카테고리 이미지 도메인
* */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryImg {

    /* 이미지 아이디 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    /* 카테고리 아이디 */
    private Long categoryId;

    /* 원본 파일명 */
    private String imgOriginName;

    /* 저장 파일명 */
    private String imgSaveName;

    /* 이미지 저장 경로 */
    private String imgPath;

    /* 이미지 url 경로 */
    private String imgUrlPath;

    /* 등록일 */
    private LocalDateTime createdAt;

    /* 수정일 */
    private LocalDateTime updatedAt;
}

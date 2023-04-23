package com.zerobase.minicommerce.admin.dto;

import com.zerobase.minicommerce.admin.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/*
* 카테고리 DTO
* */
public class CategoryDto {

    /* 카테고리 등록 요구 - 관리자 */
    @Getter
    @Setter
    @ToString
    public static class Request{
        /* 카테고리명 */
        @NotBlank(message = "카테고리명을 입력해주세요.")
        private String categoryName;

    }

    /* 카테고리 등록 응답 - 관리자 */
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

    /* 카테고리 리스트 - 관리자, 사용자 */
    @Getter @Setter
    @ToString
    public static class ListResponse{
        private Long categoryId;
        private String categoryName;
        private String imgUrlPath;
    }

    /* 사용자 - 카테고리 상세 */
    @Getter @Setter
    @ToString
    @Builder
    public static class FrontSelectResponse{
        private Long categoryId;
        private String categoryName;

        public static FrontSelectResponse from(Category category) {
            return FrontSelectResponse.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .build();
        }
    }

}

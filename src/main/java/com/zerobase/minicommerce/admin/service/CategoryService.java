package com.zerobase.minicommerce.admin.service;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    /* 카테고리 등록 - 관리자 */
    CategoryDto.Response adminSaveCategory(CategoryDto.Request request, MultipartFile fileInput) throws Exception;

    /* 카테고리 목록 조회 - 관리자, 사용자 */
    List<CategoryDto.ListResponse> categorySelectList();

    /* 카테고리 상세 - 사용자 */
    CategoryDto.FrontSelectResponse selectFrontCategory(Long categoryId);

}

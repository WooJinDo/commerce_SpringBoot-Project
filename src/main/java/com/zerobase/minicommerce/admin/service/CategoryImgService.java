package com.zerobase.minicommerce.admin.service;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.admin.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CategoryImgService {

    /* 카테고리 이미지 저장 */
    void adminSaveCategoryImg(Category category, MultipartFile fileInput) throws Exception;


}

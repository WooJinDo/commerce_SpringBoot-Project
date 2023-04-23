package com.zerobase.minicommerce.admin.service.impl;

import com.zerobase.minicommerce.admin.domain.Category;
import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.admin.mapper.CategoryMapper;
import com.zerobase.minicommerce.admin.repository.CategoryRepository;
import com.zerobase.minicommerce.admin.service.CategoryImgService;
import com.zerobase.minicommerce.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryImgService categoryImgService;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /* 카테고리 등록 - 관리자 */
    @Transactional
    @Override
    public CategoryDto.Response adminSaveCategory(CategoryDto.Request request, MultipartFile fileInput) throws Exception {

        Category category = categoryRepository.save(
                Category.builder()
                        .categoryName(request.getCategoryName())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        /* 카테고리 이미지 저장 */
        categoryImgService.adminSaveCategoryImg(category, fileInput);


        return CategoryDto.Response.fromEntity(true);
    }

    /* 카테고리 리스트 - 관리자, 사용자 */
    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto.ListResponse> categorySelectList() {
        return categoryMapper.categorySelectList();
    }

    /* 카테고리 상세 - 사용자 */
    @Transactional(readOnly = true)
    @Override
    public CategoryDto.FrontSelectResponse selectFrontCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            return CategoryDto.FrontSelectResponse.from(category);
        }
        return null;
    }

}

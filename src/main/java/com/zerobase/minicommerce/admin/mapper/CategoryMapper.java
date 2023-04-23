package com.zerobase.minicommerce.admin.mapper;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /* 카테고리 리스트 - 관리자, 사용자 */
    List<CategoryDto.ListResponse> categorySelectList();
}

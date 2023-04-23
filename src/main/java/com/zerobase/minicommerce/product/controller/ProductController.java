package com.zerobase.minicommerce.product.controller;

import com.zerobase.minicommerce.admin.dto.CategoryDto;
import com.zerobase.minicommerce.admin.service.CategoryService;
import com.zerobase.minicommerce.product.dto.ProductDto;
import com.zerobase.minicommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list.do")
    public String productList(Model model, @RequestParam(required = false) Long categoryId) {

        /* 카테고리 상세 - 사용자 */
        CategoryDto.FrontSelectResponse selectFrontCategory = null;
        if (categoryId != null) {
            selectFrontCategory = categoryService.selectFrontCategory(categoryId);
        }

        /* 사용자 - 카테고리 목록 조회 */
        List<CategoryDto.ListResponse> selectListCategory = categoryService.categorySelectList();

        /* 사용자 - 상품 목록 조회 */
        List<ProductDto.FrontProductList> selectListFrontProduct = productService.selectFrontProductList(categoryId);


        model.addAttribute("selectCategory", selectFrontCategory);
        model.addAttribute("selectListCategory", selectListCategory);
        model.addAttribute("selectListFrontProduct", selectListFrontProduct);

        return "product/list";
    }

    @GetMapping("/detail/{productId}")
    public String productDetail(Model model, @PathVariable("productId") Long productId
                                , Principal principal) {

        String email = null;
        if (principal != null) {
            email = principal.getName();
        }

        /* 사용자 - 상품 상세 조회 */
        ProductDto.FrontProductDetail selectProductDetail = productService.selectFrontProductDetail(productId);

        model.addAttribute("email", email);
        model.addAttribute("detail", selectProductDetail);

        return "product/detail";
    }
}

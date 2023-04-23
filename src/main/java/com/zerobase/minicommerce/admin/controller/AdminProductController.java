package com.zerobase.minicommerce.admin.controller;

import com.zerobase.minicommerce.admin.service.CategoryService;
import com.zerobase.minicommerce.product.dto.ProductDto;
import com.zerobase.minicommerce.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    /* 관리자 - 상품 목록 조회화면 */
    @GetMapping("/list.do")
    public String productList(Model model) {
        model.addAttribute("productList", productService.adminProductSelectList());
        return "admin/product/list";
    }

    /* 관리자 - 상품 등록화면 */
    @GetMapping("/add.do")
    public String productAdd(Model model) {
        model.addAttribute("categorySelectList", categoryService.categorySelectList());
        return "admin/product/add";
    }

    /* 관리자 - 수정화면 */
    @GetMapping("/update.do")
    public String productUpdate(@RequestParam(name = "productId") Long productId, Model model) {
        model.addAttribute("categorySelectList", categoryService.categorySelectList());
        model.addAttribute("productDetail", productService.adminProductSelectDetail(productId));
        return "admin/product/update";
    }

}

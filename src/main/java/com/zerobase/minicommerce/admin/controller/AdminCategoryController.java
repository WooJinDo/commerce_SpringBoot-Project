package com.zerobase.minicommerce.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    /* 관리자 - 카테고리 등록화면 */
    @GetMapping("/add.do")
    public String categoryAdd() {
        return "admin/category/add";
    }
}

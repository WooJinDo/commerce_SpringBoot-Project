package com.zerobase.minicommerce.main;

import com.zerobase.minicommerce.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categorySelectList", categoryService.categorySelectList());
        return "index";
    }
}

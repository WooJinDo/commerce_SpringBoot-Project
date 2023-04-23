package com.zerobase.minicommerce.cart.controller;

import com.zerobase.minicommerce.cart.dto.CartDto;
import com.zerobase.minicommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    /* 사용자 - 장바구니 화면 */
    @GetMapping("/list.do")
    public String cartList(Model model, Principal principal) {
        String email = principal.getName();

        /* 사용자 - 장바구니 목록 조회 */
        List<CartDto.CartListResponse> responses = cartService.selectCartList(email);
        model.addAttribute("cartList", responses);

        return "cart/list";
    }

}

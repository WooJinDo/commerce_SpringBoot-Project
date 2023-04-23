package com.zerobase.minicommerce.cart.controller.api;

import com.zerobase.minicommerce.cart.dto.CartDto;
import com.zerobase.minicommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/*
 * 장바구니 API
 * */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiCartController {

    private final CartService cartService;

    /* 장바구니 상품 추가 */
    @PostMapping("/api/cart/addCartProduct")
    public ResponseEntity<?> addCartProduct(@RequestBody @Valid CartDto.AddCartProductRequest request, Principal principal) {

        String email = principal.getName();
        CartDto.AddCartProductResponse response = cartService.addCartProduct(request, email);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /* 장바구니 수량 변경 */
    @PutMapping("/api/cart/updateCartProductQuantity")
    public ResponseEntity<?> updateCartProductQuantity(
            @RequestBody @Valid CartDto.UpdateCartProductQuantityRequest request, Principal principal) {

        String email = principal.getName();
        CartDto.UpdateCartProductQuantityResponse response = cartService.updateItemQuantityInCart(request, email);

        return ResponseEntity.ok().body(response);
    }

    /* 장바구니 수량 변경 */
    @DeleteMapping("/api/cart/deleteCartProduct/{cartProductIds}")
    public ResponseEntity<?> deleteCartProduct(@PathVariable("cartProductIds") String cartProductIds,Principal principal) {
        String email = principal.getName();
        cartService.deleteCartProduct(cartProductIds, email);

        return ResponseEntity.ok().body(true);
    }

//
//    @DeleteMapping("/api/cart/cartItem/{cartItemId}")
//    public ResponseEntity<?> deleteCartProduct(Model model, @PathVariable("cartItemId") Long cartItemId, Principal principal) {
//
//        String email = principal.getName();
//
//        Long deletedCartItemId = cartService.deleteItemInCart(email, cartItemId);
//
//        return ResponseEntity.ok().body("responseResult");
//    }

}

package com.zerobase.minicommerce.cart.service;

import com.zerobase.minicommerce.cart.dto.CartDto;

import java.util.List;

public interface CartService {

  /* 사용자 - 장바구니 상품 추가 */
  CartDto.AddCartProductResponse addCartProduct(CartDto.AddCartProductRequest request, String email);

  /* 사용자 - 장바구니 목록 조회 */
  List<CartDto.CartListResponse> selectCartList(String email);

  /* 사용자 - 장바구니 상품 수량 변경 */
  CartDto.UpdateCartProductQuantityResponse updateItemQuantityInCart(CartDto.UpdateCartProductQuantityRequest request, String email);

  /* 사용자 - 장바구니 상품 삭제 */
  void deleteCartProduct(String cartProductIds, String email);

}

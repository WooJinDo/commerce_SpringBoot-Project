package com.zerobase.minicommerce.cart.mapper;

import com.zerobase.minicommerce.cart.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    /* 사용자 - 장바구니 목록 조회 */
    List<CartDto.CartListResponse> selectCartList(Long userId);

    /* 사용자 - 장바구니 상품 삭제 */
    void deleteCartProduct(CartDto.DeleteCartProduct deleteCartProductDto);
}

package com.zerobase.minicommerce.common.exception.customexception;

import com.zerobase.minicommerce.common.exception.type.CartErrorCode;
import com.zerobase.minicommerce.common.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장바구니 Exception
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartException extends RuntimeException {

    private CartErrorCode cartErrorCode;
    private String errorMessage;

    public CartException(CartErrorCode cartErrorCode) {
        this.cartErrorCode = cartErrorCode;
        this.errorMessage = cartErrorCode.getDescription();
    }

}

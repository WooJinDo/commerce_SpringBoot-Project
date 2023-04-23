package com.zerobase.minicommerce.common.exception.model;

import com.zerobase.minicommerce.common.exception.type.CartErrorCode;
import lombok.*;

/**
 *  장바구니 서비스 에러 공통 응답 객체
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartErrorResponse {

  private CartErrorCode errorCode;
  private String errorMessage;
}

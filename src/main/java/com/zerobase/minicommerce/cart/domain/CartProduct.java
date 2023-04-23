package com.zerobase.minicommerce.cart.domain;

import com.zerobase.minicommerce.common.exception.customexception.CartException;
import com.zerobase.minicommerce.common.exception.type.CartErrorCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 장바구니 Entity
 * */
@Entity
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProduct {

  /* 장바구니 상품 아이디 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartProductId;

  /* 장바구니 아이디 */
  private Long cartId;

  /* 상품 아이디 */
  private Long productId;

  /* 장바구니 상품개수 */
  private int cartQuantity;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

  /* 장바구니 상품 수량 추가 */
  public void addProductStock(int cartQuantity, int stock) {
    this.cartQuantity += cartQuantity;
  }

}

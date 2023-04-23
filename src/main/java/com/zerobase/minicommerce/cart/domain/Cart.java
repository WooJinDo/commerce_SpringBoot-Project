package com.zerobase.minicommerce.cart.domain;


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
public class Cart {

  /* 장바구니 아이디 */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;

  /* 사용자 아이디 */
  private Long userId;

  /* 등록일자 */
  private LocalDateTime createdAt;

  /* 수정일자 */
  private LocalDateTime modifiedAt;

}

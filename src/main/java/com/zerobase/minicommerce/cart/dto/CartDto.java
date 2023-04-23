package com.zerobase.minicommerce.cart.dto;

import com.zerobase.minicommerce.cart.domain.CartProduct;
import com.zerobase.minicommerce.product.type.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/*
 * 장바구니 DTO
 * */
public class CartDto {

  /* 사용자 - 장바구니 상품 추가 요청 */
  @Getter @Setter
  public static class AddCartProductRequest{

    /* 상품 아이디 */
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long productId;

    /* 장바구니 상품개수 */
    @Min(value = 1, message = "장바구니에 추가할 상품의 개수는 1개 이상이어야 합니다.")
    private int cartQuantity;

  }

  /* 사용자 - 장바구니 상품 추가 응답 */
  @Getter @Setter
  @Builder
  public static class AddCartProductResponse{

    /* 장바구니 상품 아이디 */
    private Long cartProductId;

    /* 장바구니 상품개수 */
    private int cartQuantity;
    private boolean success;

    public static AddCartProductResponse from(CartProduct cartProduct) {
      return AddCartProductResponse.builder()
              .cartProductId(cartProduct.getCartProductId())
              .cartQuantity(cartProduct.getCartQuantity())
              .success(true)
              .build();
    }

  }

  /* 사용자 - 장바구니 조회 응답 */
  @Getter @Setter
  public static class CartListResponse {

    /* 장바구니 상품 아이디 */
    private Long cartProductId;

    /* 상품명 */
    private String productName;

    /* 상품가격 */
    private int productPrice;

    /* 장바구니 상품개수 */
    private int cartQuantity;

    /* 재고수량 */
    private int productStock;

    /* 판매상태 - 판매중, 품절 */
    private ProductStatus productStatus;

    /* 게시여부 */
    private boolean publishStatus;

    /* 출력 이미지 url 경로 */
    private String imgUrlPath;

  }

  /* 사용자 - 장바구니 수량 변경 요청 */
  @Getter @Setter
  public static class UpdateCartProductQuantityRequest{

    /* 장바구니 상품 아이디 */
    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long cartProductId;

    /* 장바구니 상품개수 */
    @Min(value = 1, message = "장바구니에 상품 수량의 개수는 1개 이상이어야 합니다.")
    private int cartQuantity;

    /* 재고수량 */
    private int productStock;

  }

  /* 사용자 - 장바구니 수량 변경 응답 */
  @Getter @Setter
  @Builder
  public static class UpdateCartProductQuantityResponse {
    private boolean success;

    public static UpdateCartProductQuantityResponse from(boolean success) {
      return UpdateCartProductQuantityResponse.builder()
              .success(success)
              .build();
    }
  }

  /* 사용자 - 장바구니 수량 변경 응답 */
  @Getter @Setter
  @ToString
  public static class DeleteCartProduct {
    /* 삭제할 장바구니 상품들을 담는 list */
    private List<String> cartProductList;
  }

}

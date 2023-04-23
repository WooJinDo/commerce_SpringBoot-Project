package com.zerobase.minicommerce.cart.service.impl;

import com.zerobase.minicommerce.cart.domain.Cart;
import com.zerobase.minicommerce.cart.domain.CartProduct;
import com.zerobase.minicommerce.cart.dto.CartDto;
import com.zerobase.minicommerce.cart.mapper.CartMapper;
import com.zerobase.minicommerce.cart.repository.CartProductRepository;
import com.zerobase.minicommerce.cart.repository.CartRepository;
import com.zerobase.minicommerce.cart.service.CartService;
import com.zerobase.minicommerce.common.exception.customexception.CartException;
import com.zerobase.minicommerce.common.exception.type.CartErrorCode;
import com.zerobase.minicommerce.member.domain.Member;
import com.zerobase.minicommerce.member.repository.MemberRepository;
import com.zerobase.minicommerce.product.domain.Product;
import com.zerobase.minicommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartProductRepository cartProductRepository;
  private final MemberRepository memberRepository;
  private final ProductRepository productRepository;
  private final CartMapper cartMapper;

  /*
  * 장바구니 상품 추가
  * */
  @Transactional
  @Override
  public CartDto.AddCartProductResponse addCartProduct(CartDto.AddCartProductRequest request, String email) {

    // 회원 데이터 check
    Member member = getMember(email);

    // 장바구니가 존재하지 않는다면 생성
    Cart cart = cartRepository.findByUserId(member.getId()).orElseGet(() -> createNewCart(member));

    // 상품 존재유무 check
    Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new CartException(CartErrorCode.PRODUCT_NOT_EXIST));

    // 해당상품을 재고 이상으로 수량 담는 것 불가 exception
    if (product.getProductStock() < request.getCartQuantity()) {
      throw new CartException(CartErrorCode.OVER_QUANTITY);
    }

    // 해당 상품이 CartProduct 에 존재하지 않는다면 CartProduct 생성 후 추가
    CartProduct cartProduct = cartProductRepository.
            findByCartIdAndProductId(cart.getCartId(), request.getProductId());

    if (cartProduct == null) {
      cartProduct = CartProduct.builder()
              .cartId(cart.getCartId())
              .productId(product.getProductId())
              .cartQuantity(request.getCartQuantity())
              .createdAt(LocalDateTime.now())
              .build();
      cartProductRepository.save(cartProduct);

    } else {
      // 해당 상품이 CartProduct 에 존재한다면 기존에 추가된 CartProduct 이므로 기존 data 에서 수량만 추가
      cartProduct.addProductStock(request.getCartQuantity(), product.getProductStock());

      // 장바구니에 해당 상품을 재고 이상으로 수량 담는 것 불가 exception
      if (product.getProductStock() < cartProduct.getCartQuantity()) {
        throw new CartException(CartErrorCode.CART_OVER_QUANTITY);
      }

    }
    return CartDto.AddCartProductResponse.from(cartProduct);
  }

  /*
  * 사용자 - 장바구니 목록 조회
  * */
  @Transactional(readOnly = true)
  @Override
  public List<CartDto.CartListResponse> selectCartList(String email) {

    // 회원 데이터 check
    Member member = getMember(email);
    return cartMapper.selectCartList(member.getId());
  }

  /*
  * 사용자 - 장바구니 상품 수량 변경
  * */
  @Transactional
  @Override
  public CartDto.UpdateCartProductQuantityResponse updateItemQuantityInCart(
          CartDto.UpdateCartProductQuantityRequest request, String email) {

    // 해당 상품을 재고 이상으로 수량 담는 것 불가 exception
    if (request.getProductStock() < request.getCartQuantity()) {
      throw new CartException(CartErrorCode.OVER_QUANTITY);
    }

    // 회원 데이터 check
    Member member = getMember(email);

    // 장바구니 데이터 check
    validateCartExist(member);

    // 장바구니_상품 수량 update
    CartProduct cartProduct = cartProductRepository.findById(request.getCartProductId())
            .orElseThrow(() -> new CartException(CartErrorCode.USER_CART_PRODUCT_NOT_EXIST));

    cartProduct.setCartQuantity(request.getCartQuantity());
    cartProduct.setModifiedAt(LocalDateTime.now());
    cartProductRepository.save(cartProduct);

    return CartDto.UpdateCartProductQuantityResponse.from(true);
  }

  /*
   * 사용자 - 장바구니 상품 삭제
   * */
  @Transactional
  @Override
  public void deleteCartProduct(String cartProductIds, String email) {

    // 회원 데이터 check
    Member member = getMember(email);

    // 장바구니 데이터 check
    Cart cart = validateCartExist(member);

    Long cartProductId = null;
    String[] cartProductIdArr = cartProductIds.split(",");

    List<String> cartProductList = new ArrayList<>();
    cartProductList = Arrays.asList(cartProductIdArr);

    CartDto.DeleteCartProduct deleteCartProductDto = new CartDto.DeleteCartProduct();
    deleteCartProductDto.setCartProductList(cartProductList);

    cartMapper.deleteCartProduct(deleteCartProductDto);

  }

  /*
  * 장바구니가 존재하지 않는다면 생성
  * */
  private Cart createNewCart(Member member) {
    return cartRepository.save(
                Cart.builder()
                .userId(member.getId())
                .createdAt(LocalDateTime.now())
                .build());
    }

  /*
  * 회원 데이터 check
  * */
  private Member getMember(String email) {
    return Optional.ofNullable(memberRepository.findByEmail(email))
            .orElseThrow(() -> new CartException(CartErrorCode.USER_EMAIL_NOT_EXIST));
  }


  /*
  * 장바구니 상품 data update or delete 시, 해당 회원의 장바구니 데이터 validation check
  * */
  private Cart validateCartExist(Member member) {
    return cartRepository.findByUserId(member.getId())
            .orElseThrow(() -> new CartException(CartErrorCode.USER_CART_NOT_EXIST));
  }

}

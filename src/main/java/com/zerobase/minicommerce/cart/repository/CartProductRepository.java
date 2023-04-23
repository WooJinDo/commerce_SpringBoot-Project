package com.zerobase.minicommerce.cart.repository;

import com.zerobase.minicommerce.cart.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

  CartProduct findByCartIdAndProductId(Long cartId, Long productId);

  void deleteByCartProductIdAndCartId(Long cartProductId, Long cartId);

}

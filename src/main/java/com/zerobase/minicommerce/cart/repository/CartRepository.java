package com.zerobase.minicommerce.cart.repository;


import com.zerobase.minicommerce.cart.domain.Cart;
import com.zerobase.minicommerce.cart.dto.CartDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

  Optional<Cart> findByUserId(Long userId);

}

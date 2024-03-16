package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartById(Long cartId);
}

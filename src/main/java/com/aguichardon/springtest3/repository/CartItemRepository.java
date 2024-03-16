package com.aguichardon.springtest3.repository;

import com.aguichardon.springtest3.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}

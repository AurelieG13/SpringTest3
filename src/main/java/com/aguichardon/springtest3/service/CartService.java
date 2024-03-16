package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.model.Cart;
import com.aguichardon.springtest3.model.CartItem;
import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.model.UserAuth;
import com.aguichardon.springtest3.repository.CartRepository;
import com.aguichardon.springtest3.security.jwt.JwtService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    public final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, JwtService jwtService) {
        this.cartRepository = cartRepository;
    }




    public void addItemToCart(Cart cart, Sport sport) {

        if(cart==null) {
            cart = new Cart();
        }

        CartItem cartItem = new CartItem();
        cartItem.setNameItem(sport.getName());
        cartItem.setNbSeatItem(sport.getNbSeat());
        cartItem.setPriceItem(sport.getPrice());

        cart.getCartItems().add(cartItem);
        cartRepository.save(cart);
    }
}

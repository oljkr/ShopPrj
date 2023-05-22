package kr.co.aike.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.ShoppingCart;

public interface ShoppingCartService {

	void addToCart(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

	ShoppingCart getOrCreateShoppingCart(HttpServletRequest request) throws IOException;

}

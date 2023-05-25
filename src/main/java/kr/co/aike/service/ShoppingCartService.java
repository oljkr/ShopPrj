package kr.co.aike.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.ShoppingCart;

public interface ShoppingCartService {

	void addToCart(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

	ShoppingCart getOrCreateShoppingCart(HttpServletRequest request) throws IOException;

	ModelAndView getCartList(HttpServletRequest request) throws Exception;

	void updateCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

	void removeCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

}

package kr.co.aike.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.ShoppingCart;

public interface ShoppingCartService {

	void addToCart(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception;

	ShoppingCart getOrCreateShoppingCart(HttpServletRequest request) throws IOException;
	
	void saveShoppingCart(ShoppingCart cart, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException;
	
	void saveShoppingCartCnt(String cartCnt, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException;

	ModelAndView getCartList(HttpServletRequest request) throws Exception;

	void updateCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

	void removeCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException;

}

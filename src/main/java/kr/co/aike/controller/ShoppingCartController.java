package kr.co.aike.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.ShoppingCart;
import kr.co.aike.service.ProductsService;
import kr.co.aike.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	private final ShoppingCartService service;

    @PostMapping("/add")
    public void addToCart(CartItem item, HttpServletRequest request , HttpServletResponse response) throws IOException {
    	System.out.println(item.toString());
    	service.addToCart(item, request, response);
    }

    @GetMapping("/get")
    public ShoppingCart getCart(HttpServletRequest request) throws IOException {
        return service.getOrCreateShoppingCart(request);
    }
    
}
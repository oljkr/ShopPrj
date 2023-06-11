package kr.co.aike.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.ShoppingCart;
import kr.co.aike.service.ProductsService;
import kr.co.aike.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	private final ShoppingCartService service;
	private final ProductsService prdservice;

	@ResponseBody
    @PostMapping("/add")
    public void addToCart(CartItem item, HttpServletRequest request , HttpServletResponse response) throws Exception {
    	service.addToCart(item, request, response);
    }

	@ResponseBody
    @GetMapping("/get")
    public ShoppingCart getCart(HttpServletRequest request) throws IOException {
        return service.getOrCreateShoppingCart(request);
    }
	
	@GetMapping("/getlist")
	public ModelAndView getCartList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.getCartList(request);
		return mav;
	}

	@ResponseBody
    @PostMapping("/update")
    public void updateCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.updateCartItem(item, request, response);
    }

    @PostMapping("/remove")
    public void removeCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	service.removeCartItem(item, request, response);
    }
//
//    @PostMapping("/clear")
//    public void clearCart(HttpServletResponse response) {
//        ShoppingCart cart = new ShoppingCart();
//        saveShoppingCart(cart, response);
//    }
//
    
    @ResponseBody
	@PostMapping("/searchproduct")
	public HashMap<String, List<?>> searchProduct(@RequestParam Map<String, Object> map) throws Exception {
		HashMap<String, List<?>> list = new HashMap();
		list = prdservice.searchProduct(map);
		return list;
	}
}

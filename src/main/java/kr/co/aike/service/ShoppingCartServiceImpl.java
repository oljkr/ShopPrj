package kr.co.aike.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.ShoppingCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	private static final String CART_COOKIE_NAME = "cart";
	
	public void addToCart(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	System.out.println(item.toString());
        ShoppingCart cart = getOrCreateShoppingCart(request);
        cart.addItem(item);
        saveShoppingCart(cart, response);
    }
	
    public ShoppingCart getOrCreateShoppingCart(HttpServletRequest request) throws IOException {
        ShoppingCart cart;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> cartCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(CART_COOKIE_NAME))
                    .findFirst();
            if (cartCookie.isPresent()) {
                String cartJson = cartCookie.get().getValue();
                cart = deserializeCart(cartJson);
            } else {
                cart = new ShoppingCart();
            }
        } else {
            cart = new ShoppingCart();
        }
        return cart;
    }
    
    private void saveShoppingCart(ShoppingCart cart, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        String cartJson = serializeCart(cart);
        Cookie cartCookie = new Cookie(CART_COOKIE_NAME, cartJson);
        cartCookie.setMaxAge(3600); // Cookie expiration time in seconds (e.g., 1 hour)
        response.addCookie(cartCookie);
    }


    // Serialize the shopping cart object to a JSON string and encode it
    public static String serializeCart(ShoppingCart cart) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper mapper = new ObjectMapper();
        String cartJson = mapper.writeValueAsString(cart);
        return URLEncoder.encode(cartJson, "UTF-8");
    }

    // Decode the JSON string, deserialize it to a shopping cart object
    public static ShoppingCart deserializeCart(String cartJson) throws IOException {
        String decodedCartJson = URLDecoder.decode(cartJson, "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(decodedCartJson, ShoppingCart.class);
    }
    
}

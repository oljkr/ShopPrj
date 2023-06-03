package kr.co.aike.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.aike.dao.ProductsDao;
import kr.co.aike.domain.CartItem;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.ShoppingCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	private final ProductsDao prdDao;
	private static final String CART_COOKIE_NAME = "cart";
	private static final String CARTCNT_COOKIE_NAME = "cartCnt";
	
	@Override
	public void addToCart(CartItem item, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//옵션 불러와서 제품 번호 검색
    	Products products = new Products();
    	products.setName(item.getPrdName());
    	products.setColor(item.getColor());
    	products.setSize(item.getSize());
    	products = prdDao.selectProduct(products);
    	
    	item.setPrdNo(products.getPrdNo());
    	//장바구니 쿠키 등록
        ShoppingCart cart = getOrCreateShoppingCart(request);
        cart.addItem(item);
        saveShoppingCart(cart, response);
        
        //장바구니 목록 개수 쿠키 등록
        int cnt = cart.getItems().size();
        String cartCnt = String.valueOf(cnt);
        saveShoppingCartCnt(cartCnt, response);
    }
	
	@Override
	public void updateCartItem(CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShoppingCart cart = getOrCreateShoppingCart(request);
        cart.updateItem(item);
        saveShoppingCart(cart, response);
    }
	
	@Override
	public void removeCartItem(@RequestBody CartItem item, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ShoppingCart cart = getOrCreateShoppingCart(request);
        cart.removeItem(item);
        saveShoppingCart(cart, response);
        
      //장바구니 목록 개수 쿠키 등록
        int cnt = cart.getItems().size();
        String cartCnt = String.valueOf(cnt);
        saveShoppingCartCnt(cartCnt, response);
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
    
    public void saveShoppingCart(ShoppingCart cart, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        String cartJson = serializeCart(cart);
        Cookie cartCookie = new Cookie(CART_COOKIE_NAME, cartJson);
        cartCookie.setMaxAge(3600); // Cookie expiration time in seconds (e.g., 1 hour)
        cartCookie.setPath("/"); // Set the cookie path to the root path ("/")
        response.addCookie(cartCookie);
    }
    
    public String getOrCreateShoppingCartCnt(HttpServletRequest request) throws IOException {
    	String cartCnt="";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> cartCntCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(CARTCNT_COOKIE_NAME))
                    .findFirst();
            if (cartCntCookie.isPresent()) {
                cartCnt = cartCntCookie.get().getValue();
            } else {
            	cartCnt = "";
            }
        } else {
        	cartCnt = "";
        }
        return cartCnt;
    }
    
    public void saveShoppingCartCnt(String cartCnt, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        Cookie cartCntCookie = new Cookie(CARTCNT_COOKIE_NAME, cartCnt);
        cartCntCookie.setMaxAge(3600); // Cookie expiration time in seconds (e.g., 1 hour)
        cartCntCookie.setPath("/"); // Set the cookie path to the root path ("/")
        response.addCookie(cartCntCookie);
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
    
    public ModelAndView getCartList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		ShoppingCart shoppingCart = getOrCreateShoppingCart(request);
		List<CartItem> cartItems = shoppingCart.getItems();
		mav.addObject("cartItems", cartItems);
		mav.setViewName("products/cartlist");
		return mav;
	}
    
}

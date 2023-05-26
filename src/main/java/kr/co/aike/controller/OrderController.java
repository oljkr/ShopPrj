package kr.co.aike.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Order;
import kr.co.aike.domain.Products;
import kr.co.aike.domain.ShipInfo;
import kr.co.aike.domain.Users;
import kr.co.aike.service.OrderService;
import kr.co.aike.service.ProductsService;
import kr.co.aike.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
	private final OrderService service;
	
	@GetMapping("/add")
	public ModelAndView preOrder(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.getCartList(request);
		return mav;
	}
	
	@GetMapping("/useradd")
	public ModelAndView preUserOrder(@ModelAttribute Users users, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.getCartListAndUser(users, request);
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addOrder(@ModelAttribute ShipInfo shipInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("addOrder");
		ModelAndView mav = new ModelAndView();
		mav = service.addOrder(shipInfo, request, response);
		return mav;
	}
	
	@GetMapping("/guest")
	public ModelAndView guestGet(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.guestGet(request);
		return mav;
	}
	
	@GetMapping("/getlist")
	public ModelAndView getList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.getList(request);
		return mav;
	}
	
	@PostMapping("/getdetail")
	public ModelAndView getDetail(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.getDetail(request);
		return mav;
	}
	

}

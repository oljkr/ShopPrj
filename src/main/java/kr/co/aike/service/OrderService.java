package kr.co.aike.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Order;
import kr.co.aike.domain.ShipInfo;
import kr.co.aike.domain.Users;

public interface OrderService {

	ModelAndView getCartList(HttpServletRequest request) throws Exception;
	
	ModelAndView getCartListAndUser(Users users, HttpServletRequest request) throws Exception;

	ModelAndView addOrder(ShipInfo shipInfo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception;
	
	ModelAndView guestGet(HttpServletRequest request) throws Exception;
	
	ModelAndView getList(HttpServletRequest request) throws Exception;

	ModelAndView getDetail(HttpServletRequest request) throws Exception;

	

}
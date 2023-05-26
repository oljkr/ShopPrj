package kr.co.aike.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Order;
import kr.co.aike.domain.ShipInfo;

public interface OrderService {

	ModelAndView getCartList(HttpServletRequest request) throws Exception;

	ModelAndView addOrder(ShipInfo shipInfo, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception;

}
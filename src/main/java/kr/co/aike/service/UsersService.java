package kr.co.aike.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Users;

public interface UsersService {
	
	public ModelAndView addUser(Users users) throws Exception;
	
	public ModelAndView loginUser(Users users, HttpSession session) throws Exception;
}

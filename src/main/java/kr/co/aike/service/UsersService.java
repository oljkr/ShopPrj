package kr.co.aike.service;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Users;

public interface UsersService {
	
	public ModelAndView addUser(Users users) throws Exception;
	
}

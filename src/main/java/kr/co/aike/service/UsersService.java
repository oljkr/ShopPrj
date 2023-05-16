package kr.co.aike.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Users;

public interface UsersService {
	
	public int idCheck(String checkedid) throws Exception;
	
	public ModelAndView addUser(Users users) throws Exception;
	
	public ModelAndView preLoginUser(HttpSession session, HttpServletRequest request) throws Exception;
	
	public ModelAndView loginUser(Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView logoutUser(HttpSession session, HttpServletRequest request) throws Exception;
	
	public ModelAndView findId(Users users) throws Exception;
	
	public void sendMail(String[] recipientList, String subject, String body);

	public ModelAndView findPw(Users users) throws Exception;

	public ModelAndView preUnregister() throws Exception;
	
	public ModelAndView unregister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView modifyUser(Users users, HttpSession session) throws Exception;
}

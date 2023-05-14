package kr.co.aike.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Users;
import kr.co.aike.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
	private final UsersService service;
	
	@GetMapping("/login")
	public ModelAndView usersLogin(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String temp=request.getHeader("Referer");
		System.out.println(temp);
		mav = service.preLoginUser(session, request);		
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("login");
		ModelAndView mav = new ModelAndView();
		mav = service.loginUser(users, session, request, response);
		return mav;
	}

	@GetMapping("/register")
	public String usersRegister() {
		return "users/register";
	}
	
	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute Users users) throws Exception {
		log.info("postUsers");
		ModelAndView mav = new ModelAndView();
		mav = service.addUser(users);
		return mav;
	}
	
	@GetMapping("/logout")
	public String usersLogout(HttpSession session, HttpServletRequest request) throws Exception {
		service.logoutUser(session, request);
		//return "redirect:"+request.getRequestURI()+"";
		System.out.println("logout");
		return "redirect:/home";
	}

}

package kr.co.aike.controller;

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
	public String usersLogin() {
		return "users/login";
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

}

package kr.co.aike.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.domain.Users;
import kr.co.aike.service.ProductsService;
import kr.co.aike.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
	private final UsersService service;
	private final ProductsService prdservice;
	
	@GetMapping("/login")
	public ModelAndView usersLogin(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String temp=request.getHeader("Referer");
		mav = service.preLoginUser(session, request);		
		return mav;
	}

	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.loginUser(users, session, request, response);
		return mav;
	}
	
	@PostMapping("/idcheck")
	@ResponseBody
	public int idCheck(HttpServletRequest request) throws Exception {
		String checkedid=request.getParameter("checkedid");
		int idExistCnt = service.idCheck(checkedid);
		return idExistCnt;
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
	public ModelAndView usersLogout(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = service.logoutUser(session, request);
		System.out.println("logout");
		return mav;
	}
	
	@GetMapping("/findid")
	public String findIdProc() throws Exception {
		return "users/findid";
	}
	
	@PostMapping("/findid")
	public ModelAndView findId(@ModelAttribute Users users) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.findId(users);
		return mav;
	}
	
	@GetMapping("/findpw")
	public String findPwProc() throws Exception {
		return "users/findpw";
	}
	
	@PostMapping("/findpw")
	public ModelAndView findPw(@ModelAttribute Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.findPw(users);
		return mav;
	}
	
	@GetMapping("/preunregister")
	public ModelAndView preUnregister() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.preUnregister();
		return mav;
	}
	
	@GetMapping("/unregister")
	public ModelAndView unregister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav = service.unregister(session, request, response);
		return mav;
	}
	
	@GetMapping("/modifyuser")
	public String preModifyUser(HttpSession session, HttpServletRequest request) throws Exception {
		return "users/modifyuser";
	}
	
	@PostMapping("/modifyuser")
	public ModelAndView modifyUser(@ModelAttribute Users users, HttpSession session) throws Exception {
		log.info("modifyUser");
		ModelAndView mav = new ModelAndView();
		mav = service.modifyUser(users, session);
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/searchproduct")
	public HashMap<String, List<?>> searchProduct(@RequestParam Map<String, Object> map) throws Exception {
		HashMap<String, List<?>> list = new HashMap();
		list = prdservice.searchProduct(map);
		return list;
	}

}

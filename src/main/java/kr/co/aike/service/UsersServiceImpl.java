package kr.co.aike.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.co.aike.dao.UsersDao;
import kr.co.aike.domain.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
	private final UsersDao dao;
	
	public ModelAndView addMessages(int code, String msg2Text,String link1Text,String link1Href) {
		ModelAndView mav=new ModelAndView();
		if(code==1) {
		String img="<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>";
		String msg2="<h2 class=\"mb-4\">* ~ "+msg2Text+" 성공 ~ *</h2>";			
		String link1="<input class=\"btn btn-info rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='location.href=\"./"+link1Href+"\"'>";
		mav.addObject("msg2", msg2);
		mav.addObject("img", img);
		mav.addObject("link1", link1);
		}
		return mav;
		
	}
	
	@Override
	public ModelAndView addUser(Users users) throws Exception {
		ModelAndView mav=new ModelAndView();
		log.info(users.toString());
		int cnt=dao.insertUsers(users);
		
		if(cnt==0){
			String msg1="<h1 class=\"mb-4\">!! 회원 등록 실패 !!</h1>";
			String img="<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>";
			String link1="<input class=\"btn btn-primary rounded-pill py-3 px-5\" type='button' value='다시시도' onclick='javascript:history.back()'>&nbsp;&nbsp;";
			String link2="<input class=\"btn btn-info rounded-pill py-3 px-5\" type='button' value='목록으로' onclick='location.href=\"../home.do\"'>";
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}else {
			mav=addMessages(1,"회원 등록","로그인하기","login");
		}//if end
		mav.setViewName("msgView");
		return mav;
	}
	
	@Override
	public ModelAndView loginUser(Users users, HttpSession session) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users findUsers = new Users();
		findUsers=dao.loginUser(users);
		
		String email=findUsers.getUserEmail();
		if(email==null){
			mav.addObject("msg", "아이디 혹은 비밀번호가 일치하지 않습니다. 입력한 내용을 다시 확인해 주세요.");
		}else {
			session.setAttribute("userNo", findUsers.getUserNo());
			session.setAttribute("userEmail", findUsers.getUserEmail());
			session.setAttribute("roles", findUsers.getRoles());
			mav.setViewName("index");
			System.out.println(findUsers.getRoles());
		}//if end
		
		return mav;
	}
	
	
}

package kr.co.aike.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	public ModelAndView addMessages(int code, String msg2Text, String link1Text, String link1Href) {
		ModelAndView mav=new ModelAndView();
		if(code==0) {
			String img="<div class=\"mb-3\"><i class=\"bi bi-exclamation-triangle display-1 text-primary\"></i></div>";
			String msg2="<h1 class=\"mb-4\">!! "+msg2Text+" 실패 !!</h1>";
			String link1="<input class=\"btn btn-primary rounded-pill py-3 px-5\" type='button' value='다시시도' onclick='javascript:history.back()'>&nbsp;&nbsp;";
			String link2="<input class=\"btn btn-info rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='location.href=\"../"+link1Href+"\"'>";
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		} else if(code==1) {
			String img="<div class=\"mb-3\"><i class=\"bi bi-stars text-warning\" style=\"font-size: 80px;\"></i></div>";
			String msg2="<h2 class=\"mb-4\">* ~ "+msg2Text+" 성공 ~ *</h2>";			
			String link1="<input class=\"btn btn-primary rounded-pill py-3 px-5\" type='button' value='"+link1Text+"' onclick='location.href=\"./"+link1Href+"\"'>";
			mav.addObject("msg2", msg2);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
		}
		return mav;
	}
	
	@Override
	public int idCheck(String checkedid) throws Exception {
		int idExistCnt=dao.idExist(checkedid);
		return idExistCnt;
	}
	
	@Override
	public ModelAndView addUser(Users users) throws Exception {
		ModelAndView mav=new ModelAndView();
		log.info(users.toString());
		int cnt=dao.insertUsers(users);
		
		if(cnt==0){
			mav=addMessages(0,"회원 등록","메인으로","home");
		}else {
			mav=addMessages(1,"회원 등록","로그인하기","login");
		}//if end
		mav.setViewName("msgView");
		return mav;
	}
	
	public ModelAndView rememberId(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
				
		//세션 role&쿠키 확인해서 파라미터에 추가 
	    String sRoles="";
	    Users users = new Users();
	    if(session.getAttribute("authInfo")==null){
			//세션에 값이 없으면 guest로 값주기
			users.setRoles("guest");
			session.setAttribute("authInfo", users);
		}else {
			//세션에 값이 guest이면 쿠키 확인해서 아이디 기억 여부 파라미터에 추가
			users = (Users)session.getAttribute("authInfo");
			sRoles = users.getRoles();
			if(sRoles.equals("guest")) {
				Cookie[] cookies = request.getCookies();
				String c_id = "";
				if(cookies!=null) {
					for(int i=0;i<cookies.length;i++) {
						Cookie cookie = cookies[i];
						if(cookie.getName().equals("c_id")==true) {
							c_id=cookie.getValue();
							mav.addObject("c_id",c_id);
							mav.addObject("checked","checked");
						}
					}
				}
			}
		}
	    
		return mav;
	}
	
	//쿠키에 아이디 기억 여부 저장
	public void rememberIdCookie(Users users, HttpServletRequest request, HttpServletResponse response) {
		String temp=request.getParameter("c_id");
		Cookie cookie = null;
		if(temp!=null) {
			if(temp.equals("SAVE")) {
				cookie = new Cookie("c_id",users.getUserId());
				cookie.setMaxAge(60*60*24*30);	
			}			
		}else {
			cookie = new Cookie("c_id","");
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
	}
	
	@Override
	public ModelAndView preLoginUser(HttpSession session, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();		
		mav = rememberId(session, request);
	    
	    //세션에 이전 페이지 경로 저장
		String temp=request.getHeader("Referer");
		int indexNum = temp.indexOf("aike");
		temp = temp.substring(indexNum+4);
		System.out.println(temp);
		//회원가입 후에 로그인하기 버튼을 눌러 로그인할 시 로그인 후 메인 페이지로 이동
		if(temp.equals("/users/register")) {
			temp="./../home";
		}
		session.setAttribute("place", temp);		
		mav.addObject("place", temp);
		
		mav.setViewName("users/login");
		return mav;
	}
	
	@Override
	public ModelAndView loginUser(Users users, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		Users findUsers = new Users();
		findUsers=dao.loginUser(users);
		if(findUsers==null) {
			mav = rememberId(session, request);
			rememberIdCookie(users, request, response);
			
			System.out.println("it's null");
			mav.addObject("msg", "아이디 혹은 비밀번호가 일치하지 않습니다. 입력한 내용을 다시 확인해 주세요.");
		}else {
			rememberIdCookie(users, request, response);
			
			//세션에 회원 인가 내용 저장
			session.setAttribute("authInfo", findUsers);
			
			//세션에 저장했던 이전 페이지 경로로 이동
			mav.setViewName("redirect:"+session.getAttribute("place"));
		}
		return mav;
	}
	
	@Override
	public ModelAndView logoutUser(HttpSession session, HttpServletRequest request) throws Exception {
		session.removeAttribute("authInfo");
		ModelAndView mav = new ModelAndView();
		mav = rememberId(session, request);
		
		//세션에 이전 페이지 경로 저장
		String temp=request.getHeader("Referer");
		int indexNum = temp.indexOf("aike");
		temp = temp.substring(indexNum+4);
		System.out.println(temp);
		mav.setViewName("redirect:"+ temp);
		return mav;
	}
	
}
